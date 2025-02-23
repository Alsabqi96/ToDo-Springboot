package com.codearchitects.todoapp.Models;

import com.codearchitects.todoapp.Enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User extends BaseEntity {

    @NotBlank(message = "Email can not be empty")
    @Column(nullable = false, unique = true)
    public String email;

    @NotBlank(message = "Password can not be empty")
    @Column(nullable = false)
    public String password;

    @NotBlank(message = "Username can not be empty")
    @Column(nullable = false, unique = true)
    public String userName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Role role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}



