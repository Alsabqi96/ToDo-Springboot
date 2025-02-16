// Base API URL for the Spring Boot backend
const API_URL = "http://localhost:8080/api/v1/todos";
let editTodoId = null; // Store the ID of the To-Do being edited

//  Fetch To-Dos from MySQL via Spring Boot API
async function fetchTodos() {
    const response = await fetch(API_URL); // Make a GET request to fetch all To-Dos
    const todos = await response.json(); // Parse the JSON response
    displayTodos(todos); // Call function to display To-Dos in UI
}

//  Display To-Dos in the UI
function displayTodos(todos) {
    const todoList = document.getElementById("todoList");
    todoList.innerHTML = ""; // Clear the existing list

    todos.forEach(todo => {
        const li = document.createElement("li"); // Create a new list item

        // Define the HTML structure for each To-Do
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
        todoList.appendChild(li); // Append the new list item to the UI
    });
}

//  Add a New To-Do Item
async function addTodo() {
    const title = document.getElementById("todoTitle").value;
    const description = document.getElementById("todoDescription").value;

    // Validation: Ensure title is not empty
    if (!title) {
        alert("Please enter a title");
        return;
    }

    // Send POST request to add a new To-Do item
    await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, description, completed: false })
    });

    fetchTodos(); // Refresh To-Do list
    document.getElementById("todoTitle").value = ""; // Clear input fields
    document.getElementById("todoDescription").value = "";
}

//  Toggle Completion Status of a To-Do Item
async function toggleComplete(id, completed) {
    await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ completed: !completed }) // Toggle completed status
    });

    fetchTodos(); // Refresh To-Do list
}

//  Delete a To-Do Item
async function deleteTodo(id) {
    await fetch(`${API_URL}/${id}`, {
        method: "DELETE"
    });

    fetchTodos(); // Refresh To-Do list
}

//  Open Edit Modal with Pre-Filled Data
function editTodo(id, title, description) {
    editTodoId = id; // Store the ID of the To-Do being edited
    document.getElementById("editTitle").value = title; // Fill input fields with existing data
    document.getElementById("editDescription").value = description;
    document.getElementById("editModal").style.display = "block"; // Show the edit modal
}

//  Save Edited To-Do and Send Update Request
async function saveEdit() {
    const title = document.getElementById("editTitle").value;
    const description = document.getElementById("editDescription").value;

    await fetch(`${API_URL}/${editTodoId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, description }) // Send updated title and description
    });

    closeModal(); // Close the edit modal
    fetchTodos(); // Refresh To-Do list
}

//  Close Edit Modal
function closeModal() {
    document.getElementById("editModal").style.display = "none"; // Hide the modal
}

//  Load To-Do Items on Page Load
fetchTodos();
