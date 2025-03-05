async function hashPassword(passwordToHash) {
    const encoder = new TextEncoder();
    const data = encoder.encode(passwordToHash);
    const hashBuffer = await crypto.subtle.digest("SHA-256", data);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    return hashArray.map((byte) => byte.toString(16).padStart(2, "0")).join("");
}

document.getElementById("signupForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    if (password !== confirmPassword) {
        document.getElementById("error-message").innerText = "Passwords do not match!";
        return;
    }
    const hashedPassword = await hashPassword(password);
    const response = await fetch("http://localhost:8080/auth/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            userName: username,
            email: email,
            password: hashedPassword,
            role: "USER"
        }),
    });

    if (!response.ok) {
        document.getElementById("error-message").innerText = "Signup failed. Try again.";
        return;
    }

    alert("Signup successful! You can now login.");
    window.location.href = "login.html";
});




