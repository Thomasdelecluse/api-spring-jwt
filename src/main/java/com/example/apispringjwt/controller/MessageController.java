package com.example.apispringjwt.controller;

import com.example.apispringjwt.dto.send.MessageDTO;
import com.example.apispringjwt.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping
    private void postMessage() {

    }

    ;

    @GetMapping
    private void getAllMessage() {

    }

    ;

    @GetMapping("/sent")
    private ResponseEntity<MessageDTO> getMessageByEmail() {
        return ResponseEntity.ok(new MessageDTO(messageService.getSentMessageByUser()));
    }
}
