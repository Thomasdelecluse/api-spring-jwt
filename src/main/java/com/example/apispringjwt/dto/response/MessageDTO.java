package com.example.apispringjwt.dto.response;

import com.example.apispringjwt.model.Message;

import java.util.List;

public record MessageDTO(
        List<Message> messages
){
}
