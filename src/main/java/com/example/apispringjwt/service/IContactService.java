package com.example.apispringjwt.service;

import com.example.apispringjwt.dto.response.ContactDTO;
import com.example.apispringjwt.dto.response.SearchUserDTO;

import java.util.List;

public interface IContactService {
    List<ContactDTO> getContactOfUserConnected();
    void updateContact(ContactDTO contactDTO);
    void deleteContactById(int contactId);

    List<SearchUserDTO> getContactSearch(String searchContact);
}
