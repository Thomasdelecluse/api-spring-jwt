package com.example.apispringjwt.controller;

import com.example.apispringjwt.dto.request.UserDTO;
import com.example.apispringjwt.dto.response.TokenDTO;
import com.example.apispringjwt.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    IAuthService authService;

    @PostMapping("/login")
    private ResponseEntity<TokenDTO> postLogin(@RequestBody UserDTO user) {
        return ResponseEntity.ok(new TokenDTO(authService.login(user)));

    }

    @PostMapping("/register")
    private ResponseEntity<TokenDTO> postRegister(@RequestBody UserDTO user) {
        return ResponseEntity.ok(new TokenDTO(authService.register(user)));
    }
}
