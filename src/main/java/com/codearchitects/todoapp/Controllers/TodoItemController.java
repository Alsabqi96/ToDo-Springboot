package com.codearchitects.todoapp.Controllers;

import com.codearchitects.todoapp.DTOs.TodoItemDTO;
import com.codearchitects.todoapp.RequestObjects.CreateTodoRequest;
import com.codearchitects.todoapp.RequestObjects.UpdateTodoRequest;
import com.codearchitects.todoapp.Services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/todos")
@CrossOrigin(origins = "*")
public class TodoItemController {

    @Autowired
    private TodoItemService todoItemService;

    // Get To-Do by ID
    @GetMapping(value = "{id}")
    public TodoItemDTO getTodoById(@PathVariable Long id) {
        return todoItemService.getTodoById(id);
    }

    // Get All To-Do Items
    @GetMapping(value = "getAll")
    public List<TodoItemDTO> getAllTodos() {
        return todoItemService.getAllTodos();
    }

    // Create a new To-Do item
    @PostMapping
    public TodoItemDTO createTodoItem(@RequestBody CreateTodoRequest request) {
        return todoItemService.createTodoItem(request);
    }

    // Update an existing To-Do item
    @PutMapping(value = "{id}")
    public TodoItemDTO updateTodoItem(@RequestBody UpdateTodoRequest request, @PathVariable Long id) {
        return todoItemService.updateTodoItem(id, request);
    }

    // Delete a To-Do item
    @DeleteMapping(value = "{id}")
    public String deleteTodoItemById(@PathVariable Long id) {
        todoItemService.deleteTodoItem(id);
        return "Todo item deleted successfully.";
    }
}
