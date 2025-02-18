package com.codearchitects.todoapp.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;


// Entity representing a To-Do item in the database
@Entity

public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private Boolean completed = false;

    private LocalDateTime createdAt;


    /**
     * Automatically assigns the creation timestamp before inserting into the database.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    /**
     * Gets the ID of the To-Do item.
     * @return The ID of the To-Do item.
     */
    public Long getId() {
        return id;
    }
    /**
     * Gets the title of the To-Do item.
     * @return The title of the To-Do item.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Gets the description of the To-Do item.
     * @return The description of the To-Do item.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the completion status of the To-Do item.
     * @return True if completed, otherwise false.
     */
    public Boolean getCompleted() {
        return completed;
    }
    /**
     * Gets the creation timestamp of the To-Do item.
     * @return The timestamp when the item was created.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the ID of the To-Do item.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Sets the title of the To-Do item.
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Sets the description of the To-Do item.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
