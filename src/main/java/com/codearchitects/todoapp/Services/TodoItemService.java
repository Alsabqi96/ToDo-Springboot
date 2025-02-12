package com.codearchitects.todoapp.Services;

import com.codearchitects.todoapp.DTOs.TodoItemDTO;
import com.codearchitects.todoapp.Models.TodoItem;
import com.codearchitects.todoapp.Repositories.TodoItemRepository;
import com.codearchitects.todoapp.RequestObjects.CreateTodoRequest;
import com.codearchitects.todoapp.RequestObjects.UpdateTodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository repository;


    //convert entity to DTO
    private TodoItemDTO convertToDTO(TodoItem todoItem) {
        TodoItemDTO dto = new TodoItemDTO();
        dto.setId(todoItem.getId());
        dto.setTitle(todoItem.getTitle());
        dto.setDescription(todoItem.getDescription());
        dto.setCompleted(todoItem.getCompleted());
        return dto;
    }


    //get all todo item
    public List<TodoItemDTO> getAllTodos() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    //get todo item by ID
    public TodoItemDTO getTodoById(Long id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Todo item not found"));
    }


     //create  new todo item
     public TodoItemDTO createTodoItem(CreateTodoRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Title is required");
        }

        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(request.getTitle());
        todoItem.setDescription(request.getDescription());
        todoItem.setCompleted(false);

        return convertToDTO(repository.save(todoItem));
    }


    //update  existing todo item
    public TodoItemDTO updateTodoItem(Long id, UpdateTodoRequest request) {
        return repository.findById(id).map(todo -> {
            if (request.getTitle() != null) {
                todo.setTitle(request.getTitle());
            }
            if (request.getDescription() != null) {
                todo.setDescription(request.getDescription());
            }
            if (request.getCompleted() != null) {
                todo.setCompleted(request.getCompleted());
            }
            return convertToDTO(repository.save(todo));
        }).orElseThrow(() -> new RuntimeException("Todo item not found"));
    }


    //delete todo item by ID
    @Transactional
    public void deleteTodoItem(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Todo item not found");
        }
        repository.deleteById(id);
    }
}
