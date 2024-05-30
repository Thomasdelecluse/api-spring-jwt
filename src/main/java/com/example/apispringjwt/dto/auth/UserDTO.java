package com.example.apispringjwt.dto.auth;

public record UserDTO(
        String email,
        String password
) {}