package com.example.apispringjwt.service.impl;

import com.example.apispringjwt.config.WebSocketSessionRegistryConfigurer;
import com.example.apispringjwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final WebSocketSessionRegistryConfigurer webSocketSessionRegistry;

    @Autowired
    public NotificationService(WebSocketSessionRegistryConfigurer webSocketSessionRegistry) {
        this.webSocketSessionRegistry = webSocketSessionRegistry;
    }

    public void notifyUser(User user, String message) {
        webSocketSessionRegistry.sendNotificationToUser(user, message);
    }
}
