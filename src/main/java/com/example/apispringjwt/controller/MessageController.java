package com.example.apispringjwt.controller;

import com.example.apispringjwt.dto.request.CreateMessageDTO;
import com.example.apispringjwt.dto.response.MessageDTO;
import com.example.apispringjwt.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping
    private ResponseEntity<?> postSentMessage(@RequestBody CreateMessageDTO createMessageDTO) {
        messageService.saveMessage(createMessageDTO);
        return ResponseEntity.ok().build();
    };

    @GetMapping("/allMessages")
    private ResponseEntity<MessageDTO> getAllMessages() {
        return ResponseEntity.ok(new MessageDTO(messageService.getAllMessage()));
    };

    @GetMapping
    private ResponseEntity<MessageDTO> getConversationWithContact(@RequestParam String email) {
        return ResponseEntity.ok(new MessageDTO(messageService.getConversationWithContact(email)));
    }
}
