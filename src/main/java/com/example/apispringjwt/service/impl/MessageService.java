package com.example.apispringjwt.service.impl;

import com.example.apispringjwt.exeption.ResponseEntityException;
import com.example.apispringjwt.model.Message;
import com.example.apispringjwt.repository.IMessageRepository;
import com.example.apispringjwt.service.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessage {

    @Autowired
    private IMessageRepository messageRepository;

    @Override
    public List<Message> getSentMessageByUser(){
            Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userEmail = jwt.getSubject();
            List<Message> messagesSent = messageRepository.findByAuthor(userEmail);
            if(messagesSent.isEmpty()){
                throw new ResponseEntityException(HttpStatus.NO_CONTENT, "no message found");
            }
            return messagesSent;
    }
}
