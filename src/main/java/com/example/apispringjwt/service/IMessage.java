package com.example.apispringjwt.service;

import com.example.apispringjwt.model.Message;

import java.util.List;

public interface IMessage {

    List<Message> getSentMessageByUser();
}
