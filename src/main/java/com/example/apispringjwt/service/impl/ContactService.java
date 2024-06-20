package com.example.apispringjwt.service.impl;

import com.example.apispringjwt.dto.response.ContactDTO;
import com.example.apispringjwt.exeption.ResponseEntityException;
import com.example.apispringjwt.model.Contact;
import com.example.apispringjwt.model.User;
import com.example.apispringjwt.repository.IContactRepository;
import com.example.apispringjwt.repository.IUserRepository;
import com.example.apispringjwt.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.apispringjwt.service.impl.AuthService.getUserNameConnected;

@Service
public class ContactService implements IContactService {
    @Autowired
    private IContactRepository contactRepository;
    @Autowired
    private IUserRepository userRepository;

    public List<ContactDTO> getContactOfUserConnected() {
        Optional<User> userConnected = userRepository.findByEmail(getUserNameConnected());
        if (userConnected.isPresent()) {
            List<Contact> contacts = contactRepository.findByUserId_Id(userConnected.get().getId());
            if (!contacts.isEmpty()) {
                return contacts.stream()
                        .map(contact -> new ContactDTO(contact.getContactEmail(), contact.getTelephone()))
                        .collect(Collectors.toList());
            } else {
                throw new ResponseEntityException(HttpStatus.NO_CONTENT, "No contact found");
            }
        }
        throw new ResponseEntityException(HttpStatus.NO_CONTENT, "No User Found");
    }

}
