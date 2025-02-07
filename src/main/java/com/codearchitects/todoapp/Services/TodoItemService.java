package com.codearchitects.todoapp.Services;

import com.codearchitects.todoapp.DTOs.TodoItemDTO;
import com.codearchitects.todoapp.Models.TodoItem;
import com.codearchitects.todoapp.Repositories.TodoItemRepository;
import com.codearchitects.todoapp.RequestObjects.CreateTodoRequest;
import com.codearchitects.todoapp.RequestObjects.UpdateTodoRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemRepository repository;

    private TodoItemDTO convertToDTO(TodoItem todoItem) {
        TodoItemDTO dto = new TodoItemDTO();
        dto.setId(todoItem.getId());
        dto.setTitle(todoItem.getTitle());
        dto.setDescription(todoItem.getDescription());
        dto.setCompleted(todoItem.getCompleted());
        return dto;
    }

    public List<TodoItemDTO> getAllTodos() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<TodoItemDTO> getTodoById(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    public TodoItemDTO createTodoItem(CreateTodoRequest request) {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(request.getTitle());
        todoItem.setDescription(request.getDescription());
        todoItem.setCompleted(false);
        return convertToDTO(repository.save(todoItem));
    }

    public TodoItemDTO updateTodoItem(Long id, UpdateTodoRequest request) {
        return repository.findById(id).map(todo -> {
            todo.setTitle(request.getTitle());
            todo.setDescription(request.getDescription());
            if (request.getCompleted() != null)
            {
                todo.setCompleted(request.getCompleted());
            }
            return convertToDTO(repository.save(todo));
        }).orElseThrow(() -> new RuntimeException("Todo item not found"));
    }

    public void deleteTodoItem(Long id) {
        repository.deleteById(id);
    }
}






