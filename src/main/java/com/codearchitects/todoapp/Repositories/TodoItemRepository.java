package com.codearchitects.todoapp.Repositories;

import com.codearchitects.todoapp.Models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {}

