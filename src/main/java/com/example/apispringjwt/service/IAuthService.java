package com.example.apispringjwt.service;

import com.example.apispringjwt.dto.auth.UserDTO;

public interface IAuthService {
    String login(UserDTO login) throws Exception;

    String register(UserDTO login) throws Exception;
}
