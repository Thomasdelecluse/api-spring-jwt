package com.example.apispringjwt.service.impl;

import com.example.apispringjwt.dto.request.UserDTO;
import com.example.apispringjwt.exeption.ResponseEntityException;
import com.example.apispringjwt.model.User;
import com.example.apispringjwt.repository.IUserRepository;
import com.example.apispringjwt.service.IAuthService;
import com.example.apispringjwt.service.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IJwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(UserDTO login){
        User loggedUser = userRepository.findByEmail(login.email())
                .filter(user -> passwordEncoder.matches(login.password(), user.getPassword()))
                .orElseThrow(() -> new ResponseEntityException(HttpStatus.NO_CONTENT, "user not found"));

        return jwtService.generateToken(new UsernamePasswordAuthenticationToken(
                loggedUser, null, loggedUser.getAuthorities()
        ));
    }

    @Override
    public String register(UserDTO login) {
            if (login == null || login.email() == null || login.password() == null) {
                throw new ResponseEntityException(HttpStatus.NO_CONTENT, "login null");
            }

            User user = new User();
            user.setEmail(login.email());
            user.setPassword(passwordEncoder.encode(login.password()));
            userRepository.save(user);

            return jwtService.generateToken(new UsernamePasswordAuthenticationToken(
                    user.getEmail(), null, user.getAuthorities()
            ));
    }
    public static String getUserNameConnected() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = jwt.getSubject();
        return userEmail;
    }

}
