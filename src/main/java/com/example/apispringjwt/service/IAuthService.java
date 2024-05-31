package com.example.apispringjwt.service;

import com.example.apispringjwt.dto.request.UserDTO;

public interface IAuthService {
    String login(UserDTO login);

    String register(UserDTO login);
}
