document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const userName = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch("http://localhost:8080/auth/signin", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ userName, password })
        });

        if (!response.ok) throw new Error("Invalid credentials");

        const result = await response.json();
        localStorage.setItem("token", result.token);
        window.location.href = "index.html";

    } catch (error) {
        document.getElementById("error-message").textContent = error.message;
    }
});





