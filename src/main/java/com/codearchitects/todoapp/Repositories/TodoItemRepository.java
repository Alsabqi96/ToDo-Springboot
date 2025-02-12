package com.codearchitects.todoapp.Repositories;

import com.codearchitects.todoapp.Models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//repository interface fot todo item
//extends jparepository to provide built in CRUD methods
@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}