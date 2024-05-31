package com.example.apispringjwt.service.impl;

import com.example.apispringjwt.dto.request.CreateMessageDTO;
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

import static com.example.apispringjwt.service.impl.AuthService.getUserNameConnected;

@Service
public class MessageService implements IMessage {

    @Autowired
    private IMessageRepository messageRepository;

    @Override
    public List<Message> getSentMessageByUser(){
        String userEmail = getUserNameConnected();
        List<Message> messagesReceive = messageRepository.findByAuthor(userEmail);
            if(messagesReceive.isEmpty()){
                throw new ResponseEntityException(HttpStatus.NO_CONTENT, "no message found");
            }
            return messagesReceive;
    }

    @Override
    public List<Message> getReceiveMessageByUser(){
        String userEmail = getUserNameConnected();
        List<Message> messagesSent = messageRepository.findByDestination(userEmail);
        if(messagesSent.isEmpty()){
            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "no message found");
        }
        return messagesSent;
    }

    @Override
    public void saveMessage(CreateMessageDTO createMessageDTO) {
        if(createMessageDTO == null || createMessageDTO.author() == null || createMessageDTO.message() == null){
            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "no content found in request");
        }
        Message messageToSave = new Message();
        messageToSave.setAuthor(createMessageDTO.author());
        messageToSave.setMessage(createMessageDTO.message());
        messageToSave.setDestination(createMessageDTO.destination());

        messageRepository.save(messageToSave);
    }

    @Override
    public List<Message> getAllMessage(){
        List<Message> allMessages = messageRepository.findAll();
        if(allMessages.isEmpty()){
            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "no messages found");
        }
        return allMessages;
    }


}
