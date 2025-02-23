package com.codearchitects.todoapp.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "abcd";
    private static final long EXPIRATION_TIME = 86_400_000;

    // Generate JWT token with additional claims (email, role)
    public String generateToken(String username, String email, String role) {
        return Jwts.builder()
                .setSubject(username) // Set the username as the subject (standard claim)
                .claim("email", email) // Add the email as a custom claim
                .claim("role", role) // Add the role as a custom claim
                .setIssuedAt(new Date()) // Set the issue date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Sign with the secret key
                .compact(); // Create the JWT
    }

    // Extract the email claim from the token
    public String extractEmail(String token) {
        return extractClaims(token).get("email", String.class);
    }


    // Extract the role claim from the token
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    public boolean isTokenValid(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}

