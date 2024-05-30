package com.example.apispringjwt.service;

import org.springframework.security.core.Authentication;

public interface IJwtService {
    String generateToken(Authentication authentication);
}
