package com.example.apispringjwt.config;

import com.example.apispringjwt.model.User;
import com.example.apispringjwt.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class WebSocketSessionRegistryConfigurer implements ChannelInterceptor {

    private final JwtService jwtService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final Map<Integer, String> sessionByUserIdMap = new HashMap<>();

    @Autowired
    public WebSocketSessionRegistryConfigurer(JwtService jwtService, SimpMessagingTemplate simpMessagingTemplate) {
        this.jwtService = jwtService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        List<String> authorizeHeader = accessor.getNativeHeader("Authorization");

        if (authorizeHeader == null) {
            return null;
        }

        String token = authorizeHeader.get(0).replace("Bearer ", "");
        User user = jwtService.decodeUserFromToken(token);

        accessor.setUser(new UsernamePasswordAuthenticationToken(user, token, user.getAuthorities()));

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            sessionByUserIdMap.put(user.getId(), accessor.getSessionId());
        }

        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            if (!Objects.requireNonNull(accessor.getDestination()).endsWith(user.getEmail())) {
                return null;
            }
        }

        if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
            sessionByUserIdMap.remove(user.getId());
        }

        return message;
    }


    public void sendNotificationToUser(User user, Object payload) {
        simpMessagingTemplate.convertAndSend("/topic/user/" + user.getEmail().toLowerCase(), payload);

    }
}
