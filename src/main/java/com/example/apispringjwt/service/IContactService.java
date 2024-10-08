package com.example.apispringjwt.service;

import com.example.apispringjwt.dto.response.ContactDTO;
import com.example.apispringjwt.dto.response.SearchUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IContactService {
    List<ContactDTO> getContactOfUserConnected();
    void updateContact(ContactDTO contactDTO);
    void deleteContactById(int contactId);

    List<SearchUserDTO> getContactSearch(String searchContact);
}
