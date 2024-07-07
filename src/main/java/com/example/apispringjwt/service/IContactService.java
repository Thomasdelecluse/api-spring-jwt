package com.example.apispringjwt.service;

import com.example.apispringjwt.dto.response.ContactDTO;
import com.example.apispringjwt.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IContactService {
    List<ContactDTO> getContactOfUserConnected();
    void updateContact(ContactDTO contactDTO);
}
