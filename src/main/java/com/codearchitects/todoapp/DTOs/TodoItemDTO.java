package com.codearchitects.todoapp.DTOs;


//data transfer object for todo items
public class TodoItemDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;

     // Setters and Getters
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
    /**
     * Sets the title of the To-Do item.
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Gets the description of the To-Do item.
     * @return The description of the To-Do item.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the To-Do item.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Gets the completion status of the To-Do item.
     * @return True if completed, otherwise false.
     */
    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

}