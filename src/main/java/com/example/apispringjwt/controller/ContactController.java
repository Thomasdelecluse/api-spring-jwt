package com.example.apispringjwt.controller;

import com.example.apispringjwt.dto.response.ContactDTO;
import com.example.apispringjwt.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    IContactService contactService;

    @GetMapping()
    private ResponseEntity<List<ContactDTO>> getContactOfUserConnected() {
        return ResponseEntity.ok(contactService.getContactOfUserConnected());
    };
}
