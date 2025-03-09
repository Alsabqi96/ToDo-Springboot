package com.codearchitects.todoapp.RequestObjects;


//request object for updating existing todo item
public class UpdateTodoRequest {
    private String title;
    private String description;
    private Boolean completed;


    //setters and getters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

}



