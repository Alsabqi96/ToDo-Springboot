package com.codearchitects.todoapp.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequestDTO {

     String username;
     String password;
}






