package com.example.apispringjwt.controller;

import com.example.apispringjwt.dto.receive.UserDTO;
import com.example.apispringjwt.dto.send.TokenDTO;
import com.example.apispringjwt.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ResponseEntity<TokenDTO> postLogin(@RequestBody UserDTO user) {
        return ResponseEntity.ok(new TokenDTO(authService.login(user)));

    }

    @PostMapping("/register")
    private ResponseEntity<TokenDTO> postRegister(@RequestBody UserDTO user) {
        return ResponseEntity.ok(new TokenDTO(authService.register(user)));
    }
}
