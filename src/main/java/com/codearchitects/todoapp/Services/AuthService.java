package com.codearchitects.todoapp.Services;

import com.codearchitects.todoapp.Repositories.UserRepository;
import com.codearchitects.todoapp.DTOs.SignInRequestDTO;
import com.codearchitects.todoapp.DTOs.SignUpRequestDTO;
import com.codearchitects.todoapp.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.codearchitects.todoapp.Models.User;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    // Sign-in method for JWT generation
    public String signIn(SignInRequestDTO dto) throws Exception {
        // Find user by username
        User user = userRepository.findByUserName(dto.getUserName());
        // Check password match
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new Exception("Invalid username or password");
        }

        // Generate JWT token
        return jwtUtil.generateToken(user.getUserName(), user.getEmail(), user.getRole().name());
    }

    public void signUp(SignUpRequestDTO dto) throws Exception {
        if (userRepository.findByUserName(dto.getUserName()) != null) {
            throw new Exception("Username already exists");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new Exception("Email already exists");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUserName(dto.getUserName());
        user.setRole(dto.getRole());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }
}
