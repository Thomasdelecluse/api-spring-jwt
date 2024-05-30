package com.example.apispringjwt.controller;

import com.example.apispringjwt.JwtService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public String submitData(@RequestBody String requestData) {
        return jwtService.generateToken(requestData);
    }
}
