package com.codearchitects.todoapp.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





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

}



