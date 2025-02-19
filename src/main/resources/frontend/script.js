const API_URL = "http://localhost:8080/api/v1/todos";
let editTodoId = null;

// Fetch To-Dos from API
async function fetchTodos() {
    try {
        const response = await fetch(API_URL);
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

    if (!title) {
        alert("Please enter a task title.");
        return;
    }

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
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
    try {
        await fetch(`${API_URL}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ completed: !completed })
        });
        fetchTodos();
    } catch (error) {
        console.error("Error toggling todo:", error);
    }
}

// Delete To-Do
async function deleteTodo(id) {
    try {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
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

    try {
        await fetch(`${API_URL}/${editTodoId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
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
