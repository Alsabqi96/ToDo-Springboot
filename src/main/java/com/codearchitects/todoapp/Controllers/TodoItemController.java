package com.codearchitects.todoapp.Controllers;
import com.codearchitects.todoapp.DTOs.TodoItemDTO;
import com.codearchitects.todoapp.RequestObjects.CreateTodoRequest;
import com.codearchitects.todoapp.RequestObjects.UpdateTodoRequest;
import com.codearchitects.todoapp.Services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/todos")
public class TodoItemController {

    private static final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    @Autowired
    private TodoItemService service;


    //get all todo items
    @GetMapping
    public ResponseEntity<List<TodoItemDTO>> getAllTodos() {
        List<TodoItemDTO> todos = service.getAllTodos();
        return todos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(todos);
    }


    //get todo item by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getTodoById(id));
        } catch (RuntimeException e) {
            logger.error("Error fetching To-Do with ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    //create  new todo item
    @PostMapping
    public ResponseEntity<?> createTodoItem(@RequestBody CreateTodoRequest request) {
        try {
            TodoItemDTO createdTodo = service.createTodoItem(request);
            return ResponseEntity.status(201).body(createdTodo);
        } catch (Exception e) {
            logger.error("Error creating To-Do: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error creating To-Do item: " + e.getMessage());
        }
    }


    //update  existing todo item
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodoItem(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
        try {
            TodoItemDTO updatedTodo = service.updateTodoItem(id, request);
            return ResponseEntity.ok(updatedTodo);
        } catch (RuntimeException e) {
            logger.error("Error updating To-Do with ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    //delete  todo item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoItem(@PathVariable Long id) {
        try {
            service.deleteTodoItem(id);
            return ResponseEntity.ok("Todo item deleted successfully");
        } catch (RuntimeException e) {
            logger.error("Error deleting To-Do with ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
