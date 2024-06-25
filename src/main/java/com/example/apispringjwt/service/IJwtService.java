package com.example.apispringjwt.service;

import com.example.apispringjwt.model.User;
import org.springframework.security.core.Authentication;

public interface IJwtService {
    String generateToken(Authentication authentication);

    User decodeUserFromToken(String token);
}
