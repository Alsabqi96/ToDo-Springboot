const API_URL = "http://localhost:8080/api/v1/todos";

// Fetch and display To-Do items
async function fetchTodos() {
    const response = await fetch(API_URL);
    const todos = await response.json();

    const list = document.getElementById("todo-list");
    list.innerHTML = "";

    todos.forEach(todo => {
        const li = document.createElement("li");
        li.className = todo.completed ? "completed" : "";
        li.innerHTML = `
            <span>${todo.title} - ${todo.description}</span>
            <div>
                <button class="complete-btn" onclick="toggleComplete(${todo.id}, ${!todo.completed})">
                    ${todo.completed ? "Undo" : "Complete"}
                </button>
                <button class="edit-btn" onclick="editTodo(${todo.id})">Edit</button>
                <button class="delete-btn" onclick="deleteTodo(${todo.id})">Delete</button>
            </div>
        `;
        list.appendChild(li);
    });
}

// Add a new To-Do item
async function addTodo() {
    const title = document.getElementById("todo-title").value;
    const description = document.getElementById("todo-description").value;

    if (!title.trim()) {
        alert("Title cannot be empty!");
        return;
    }

    await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, description })
    });

    document.getElementById("todo-title").value = "";
    document.getElementById("todo-description").value = "";
    fetchTodos();
}

// Edit a To-Do item
async function editTodo(id) {
    const newTitle = prompt("Enter new title:");
    const newDescription = prompt("Enter new description:");

    if (newTitle !== null && newDescription !== null) {
        await fetch(`${API_URL}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ title: newTitle, description: newDescription, completed: false })
        });
        fetchTodos();
    }
}

// Toggle completion status
async function toggleComplete(id, status) {
    await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ completed: status })
    });
    fetchTodos();
}

// Delete a To-Do item
async function deleteTodo(id) {
    if (confirm("Are you sure you want to delete this task?")) {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        fetchTodos();
    }
}

// Load To-Do items on page load
document.addEventListener("DOMContentLoaded", fetchTodos);
