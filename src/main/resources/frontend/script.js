const API_URL = "http://localhost:8080/api/v1/todos";
let editTodoId = null;

// fetch todo from MySQL via Spring Boot API
async function fetchTodos() {
    const response = await fetch(API_URL);
    const todos = await response.json();
    displayTodos(todos);
}

// display todo in the UI
function displayTodos(todos) {
    const todoList = document.getElementById("todoList");
    todoList.innerHTML = "";

    todos.forEach(todo => {
        const li = document.createElement("li");
        li.innerHTML = `
            <span class="${todo.completed ? 'completed' : ''}">
                ${todo.title} - ${todo.description}
            </span>
            <div class="action-buttons">
                <button class="complete-btn" onclick="toggleComplete(${todo.id}, ${todo.completed})">✔</button>
                <button class="edit-btn" onclick="editTodo(${todo.id}, '${todo.title}', '${todo.description}')">✏</button>
                <button class="delete-btn" onclick="deleteTodo(${todo.id})">🗑️</button>
            </div>
        `;
        todoList.appendChild(li);
    });
}

// add new todo
async function addTodo() {
    const title = document.getElementById("todoTitle").value;
    const description = document.getElementById("todoDescription").value;

    if (!title) {
        alert("Please enter a title");
        return;
    }

    await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, description, completed: false })
    });

    fetchTodos();
    document.getElementById("todoTitle").value = "";
    document.getElementById("todoDescription").value = "";
}

// toggle complete status
async function toggleComplete(id, completed) {
    await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ completed: !completed })
    });

    fetchTodos();
}

// delete todo
async function deleteTodo(id) {
    await fetch(`${API_URL}/${id}`, {
        method: "DELETE"
    });

    fetchTodos();
}

// open edit modal
function editTodo(id, title, description) {
    editTodoId = id;
    document.getElementById("editTitle").value = title;
    document.getElementById("editDescription").value = description;
    document.getElementById("editModal").style.display = "block";
}

// save edited Todo
async function saveEdit() {
    const title = document.getElementById("editTitle").value;
    const description = document.getElementById("editDescription").value;

    await fetch(`${API_URL}/${editTodoId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, description })
    });

    closeModal();
    fetchTodos();
}

// close edit modal
function closeModal() {
    document.getElementById("editModal").style.display = "none";
}

// load todo on page load
fetchTodos();


