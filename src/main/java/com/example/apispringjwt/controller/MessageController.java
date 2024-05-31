package com.example.apispringjwt.controller;

import com.example.apispringjwt.dto.request.CreateMessageDTO;
import com.example.apispringjwt.dto.response.MessageDTO;
import com.example.apispringjwt.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping
    private ResponseEntity<?> postSentMessage(@RequestBody CreateMessageDTO createMessageDTO) {
        messageService.saveMessage(createMessageDTO);
        return ResponseEntity.ok().build();
    };

    @GetMapping
    private ResponseEntity<MessageDTO> getAllMessage() {
        return ResponseEntity.ok(new MessageDTO(messageService.getAllMessage()));
    };

    @GetMapping("/sent")
    private ResponseEntity<MessageDTO> getMessageSentByEmail() {
        return ResponseEntity.ok(new MessageDTO(messageService.getSentMessageByUser()));
    }

    @GetMapping("/receive")
    private ResponseEntity<MessageDTO> getMessageReceiveByEmail() {
        return ResponseEntity.ok(new MessageDTO(messageService.getReceiveMessageByUser()));
    }
}
