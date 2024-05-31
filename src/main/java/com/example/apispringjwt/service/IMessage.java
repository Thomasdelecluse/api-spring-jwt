package com.example.apispringjwt.service;

import com.example.apispringjwt.dto.request.CreateMessageDTO;
import com.example.apispringjwt.model.Message;

import java.util.List;

public interface IMessage {
    void saveMessage(CreateMessageDTO createMessageDTO);
    List<Message> getAllMessage();
    List<Message> getSentMessageByUser();

    List<Message> getReceiveMessageByUser();
}
