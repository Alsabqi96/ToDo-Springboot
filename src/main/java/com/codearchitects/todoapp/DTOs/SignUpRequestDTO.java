package com.codearchitects.todoapp.DTOs;

import com.codearchitects.todoapp.Enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequestDTO {

    @NotBlank(message = "Username is required")
    String userName;

    @NotBlank(message = "Password is required")
    String password;

    @Email(message = "Valid email is required")
    String email;

    @NotNull(message = "Role is required")
    Role role;

}








