package com.codearchitects.todoapp.Repositories;

import com.codearchitects.todoapp.Models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for To-Do items.
 * Extends JpaRepository to provide built-in CRUD operations.
 */
@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}