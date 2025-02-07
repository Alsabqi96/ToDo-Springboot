package com.codearchitects.todoapp.Controllers;
import com.codearchitects.todoapp.DTOs.TodoItemDTO;
import com.codearchitects.todoapp.RequestObjects.CreateTodoRequest;
import com.codearchitects.todoapp.RequestObjects.UpdateTodoRequest;
import com.codearchitects.todoapp.Services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoItemController {
    @Autowired
    private TodoItemService service;

    @GetMapping
    public List<TodoItemDTO> getAllTodos() {
        return service.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getTodoById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo item not found: " + e.getMessage());
        }
    }

    @PostMapping
    public TodoItemDTO createTodoItem(@RequestBody CreateTodoRequest request) {
        return service.createTodoItem(request);
    }

    @PostMapping("/{id}")
    public TodoItemDTO updateTodoItem(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
        return service.updateTodoItem(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable Long id) {
        service.deleteTodoItem(id);
    }
}
