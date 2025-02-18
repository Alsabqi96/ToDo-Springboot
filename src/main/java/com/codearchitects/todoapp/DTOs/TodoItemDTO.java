package com.codearchitects.todoapp.DTOs;


//data transfer object for todo items
public class TodoItemDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;


    /**
     * Gets the ID of the To-Do item.
     * @return The ID of the To-Do item.
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the ID of the To-Do item.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Gets the title of the To-Do item.
     * @return The title of the To-Do item.
     */
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