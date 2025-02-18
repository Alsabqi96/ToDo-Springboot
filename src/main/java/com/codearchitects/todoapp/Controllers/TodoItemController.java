package com.codearchitects.todoapp.Controllers;

/*Import necessary packages*/
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

/*Define a REST controller for todo items*/
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/todos")
public class TodoItemController {

    /*Logger instance for logging errors and info*/
    private static final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    /*Inject the todo service*/
    @Autowired
    private TodoItemService service;

     /*
     fetches all todo items
     return list of Todo items dto objects
     */     
    @GetMapping
    public ResponseEntity<List<TodoItemDTO>> getAllTodos() {
        // Retrieve all To-Do items from the service
        List<TodoItemDTO> todos = service.getAllTodos();
        //  Retrieve all To-Do items from the service
        return todos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(todos);
    }

    /*
    fetches  todo item by ID
    return the corresponding todo item dto
    */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        try {
            // Fetch a specific todo item by ID
            return ResponseEntity.ok(service.getTodoById(id));
        } catch (RuntimeException e) {
            logger.error("Error fetching To-Do with ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    // creates a new todo item
    // return the created todo item dto or an error response
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


    // updates an   existing todo item
    // return The updated TodoItemDTO or 404 if not found
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


    // deletes a  todo item by ID
    // return success message or 404 if not found
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

