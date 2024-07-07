package com.example.apispringjwt.service.impl;

import com.example.apispringjwt.dto.response.ContactDTO;
import com.example.apispringjwt.exeption.ResponseEntityException;
import com.example.apispringjwt.model.Contact;
import com.example.apispringjwt.model.User;
import com.example.apispringjwt.repository.IContactRepository;
import com.example.apispringjwt.repository.IUserRepository;
import com.example.apispringjwt.service.IContactService;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Base64;
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

    @Override
    public List<ContactDTO> getContactOfUserConnected() {
        Optional<User> userConnected = userRepository.findByEmail(getUserNameConnected());
        if (userConnected.isPresent()) {
            List<Contact> contacts = contactRepository.findByUserId_Id(userConnected.get().getId());
            if (!contacts.isEmpty()) {
                try {
                    return contacts.stream()
                            .map(contact -> new ContactDTO(
                                    contact.getId(),
                                    contact.getContactEmail(),
                                    contact.getTelephone(),
                                    contact.getContactName(),
                                    contact.getImage() != null ? Base64.getEncoder().encodeToString(contact.getImage()) : null
                            ))
                            .collect(Collectors.toList());
                } catch (Exception e) {
                    throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting contact list");
                }
            } else {
                throw new ResponseEntityException(HttpStatus.NO_CONTENT, "No contact found");
            }
        }
        throw new ResponseEntityException(HttpStatus.NO_CONTENT, "No User Found");
    }

    @Override
    public void updateContact(ContactDTO contactDTO) {
        Optional<User> userConnected = userRepository.findByEmail(getUserNameConnected());
        try {
            if (userConnected.isPresent()) {
                Contact contact = new Contact();
                contact.setId(contactDTO.id());
                contact.setContactEmail(contactDTO.contactEmail());
                contact.setTelephone(contactDTO.telephone());
                contact.setContactName(contactDTO.contactName());
                contact.setUserId(userConnected.get());
                if(contactDTO.image() != null){
                    byte[] image = Base64.getDecoder().decode(contactDTO.image().toString());
                    contact.setImage(image);
                }
                contactRepository.saveAndFlush(contact);
            }else {
                throw new ResponseEntityException(HttpStatus.NO_CONTENT, "User not connected not found");
            }

        } catch (Exception e) {
            throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while updating contact");
        }
    }

}
