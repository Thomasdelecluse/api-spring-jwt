package com.example.apispringjwt.controller;

import com.example.apispringjwt.dto.auth.TokenDTO;
import com.example.apispringjwt.dto.auth.UserDTO;
import com.example.apispringjwt.service.IAuthService;
import com.example.apispringjwt.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok(new TokenDTO(authService.login(user)));
        }
        catch (Exception e ){
            return (ResponseEntity<TokenDTO>) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok(new TokenDTO(authService.register(user)));
        }
        catch (IllegalAccessException e ){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
