package com.codearchitects.todoapp.RequestObjects;


//request object for creating new todo item
public class CreateTodoRequest {
    private String title;
    private String description;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}