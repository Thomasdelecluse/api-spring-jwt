package com.example.apispringjwt.controller;

import com.example.apispringjwt.dto.response.ContactDTO;
import com.example.apispringjwt.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping()
    private ResponseEntity<?> updateContactOfUserConnected(@RequestBody ContactDTO contactDTO) {
        contactService.updateContact(contactDTO);
        return ResponseEntity.ok().build();
    };

}
