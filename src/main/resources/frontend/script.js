const API_URL = "http://localhost:8080/api/v1/todos";
let editTodoId = null;

// Check if user is authenticated
if (localStorage.getItem("token") == null) {
    window.location.href = "login.html"; // Redirect to login page if not authenticated
}

// Fetch To-Dos from API
async function fetchTodos() {
    const token = localStorage.getItem("token");
    if (!token) {
        swal("Unauthorized", "You need to log in first.", "error");
        return;
    }
    try {
        const response = await fetch(API_URL, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });
        if (response.status === 401) {
            swal("Unauthorized", "Session expired. Please log in again.", "error");
            localStorage.removeItem("token");
            window.location.href = "sign-in.html";
            return;
        }
        if (!response.ok) throw new Error("Failed to fetch");
        const todos = await response.json();
        displayTodos(todos);
    } catch (error) {
        console.error("Error fetching todos:", error);
    }
}

// Display To-Do Items
function displayTodos(todos) {
    const todoList = document.getElementById("todoList");
    todoList.innerHTML = "";
    todos.forEach(todo => {
        const li = document.createElement("li");
        li.classList.add("list-group-item");
        li.innerHTML = `
            <span class="${todo.completed ? 'completed' : ''}">${todo.title} - ${todo.description}</span>
            <div>
                <button class="btn btn-success btn-sm" onclick="toggleComplete(${todo.id}, ${todo.completed})">✔</button>
                <button class="btn btn-warning btn-sm" onclick="editTodo(${todo.id}, '${todo.title}', '${todo.description}')">✏</button>
                <button class="btn btn-danger btn-sm" onclick="deleteTodo(${todo.id})">🗑️</button>
            </div>
        `;
        todoList.appendChild(li);
    });
}

// Add New To-Do
async function addTodo() {
    const title = document.getElementById("todoTitle").value.trim();
    const description = document.getElementById("todoDescription").value.trim();
    const token = localStorage.getItem("token");

    if (!token) {
        swal("Unauthorized", "You need to log in first.", "error");
        return;
    }
    if (!title) {
        swal("Warning", "Please enter a task title.", "warning");
        return;
    }
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({ title, description, completed: false })
        });
        if (!response.ok) throw new Error("Failed to add task");
        fetchTodos();
        document.getElementById("todoTitle").value = "";
        document.getElementById("todoDescription").value = "";
    } catch (error) {
        console.error("Error adding todo:", error);
    }
}

// Toggle To-Do Completion
async function toggleComplete(id, completed) {
    const token = localStorage.getItem("token");

    if (!token) {
        swal("Unauthorized", "You need to log in first.", "error");
        return;
    }

    try {
        await fetch(`${API_URL}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({ completed: !completed })
        });

        fetchTodos();
    } catch (error) {
        console.error("Error toggling todo:", error);
    }
}

// Delete To-Do
async function deleteTodo(id) {
    const token = localStorage.getItem("token");

    if (!token) {
        swal("Unauthorized", "You need to log in first.", "error");
        return;
    }

    try {
        await fetch(`${API_URL}/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        fetchTodos();
    } catch (error) {
        console.error("Error deleting todo:", error);
    }
}

// Open Edit Modal
function editTodo(id, title, description) {
    editTodoId = id;
    document.getElementById("editTitle").value = title;
    document.getElementById("editDescription").value = description;
    new bootstrap.Modal(document.getElementById("editModal")).show();
}

// Save Edited To-Do
async function saveEdit() {
    const title = document.getElementById("editTitle").value.trim();
    const description = document.getElementById("editDescription").value.trim();
    const token = localStorage.getItem("token");

    if (!token) {
        swal("Unauthorized", "You need to log in first.", "error");
        return;
    }

    try {
        await fetch(`${API_URL}/${editTodoId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({ title, description })
        });

        fetchTodos();
        bootstrap.Modal.getInstance(document.getElementById("editModal")).hide();
    } catch (error) {
        console.error("Error saving edit:", error);
    }
}
// Load To-Dos on Page Load
fetchTodos();


