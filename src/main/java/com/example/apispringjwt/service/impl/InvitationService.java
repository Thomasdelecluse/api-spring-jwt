package com.example.apispringjwt.service.impl;

import com.example.apispringjwt.dto.request.InvitationDTO;
import com.example.apispringjwt.exeption.ResponseEntityException;
import com.example.apispringjwt.model.Contact;
import com.example.apispringjwt.model.Invitation;
import com.example.apispringjwt.model.User;
import com.example.apispringjwt.repository.IContactRepository;
import com.example.apispringjwt.repository.IInvitationRepository;
import com.example.apispringjwt.repository.IUserRepository;
import com.example.apispringjwt.service.IInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.apispringjwt.enumeration.InvitationStatus.EN_ATTENTE;
import static com.example.apispringjwt.service.impl.AuthService.getUserNameConnected;

@Service
public class InvitationService implements IInvitationService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IInvitationRepository invitationRepository;

    @Autowired
    private IContactRepository contactRepository;
    @Override
    public void addInvitation(InvitationDTO invitationDTO) {
        if (invitationDTO == null){
            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "No User found with your id");
        }
        try {
            String userEmail = getUserNameConnected();
            Optional<User> userConnected = userRepository.findByEmail(userEmail);

            Optional<User> userToAdd = userRepository.findByEmail(invitationDTO.contactEmail());

            if (userToAdd.isEmpty()){
                throw new ResponseEntityException(HttpStatus.NO_CONTENT, "No User found with this email");
            }
            if (userConnected.isPresent()){
                Invitation invitation = new Invitation(userConnected.get(), invitationDTO.contactEmail(), userToAdd.get().getName(), EN_ATTENTE);
                invitationRepository.save(invitation);
            }
        }catch (Exception e){
            throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Error when saving invitation");
        }
    }

    @Override
    public void acceptInvitation(int id) {

        try {
            String userEmail = getUserNameConnected();
            Optional<User> userConnected = userRepository.findByEmail(userEmail);

            if (userConnected.isEmpty()) {
                throw new ResponseEntityException(HttpStatus.NO_CONTENT, "No connected user found");
            }

            Optional<Invitation> invitationOpt = invitationRepository.findById(id);

            if (invitationOpt.isEmpty()) {
                throw new ResponseEntityException(HttpStatus.NO_CONTENT, "No Invitation found with this ID");
            }

            Invitation invitation = invitationOpt.get();

            if (invitation.getContactEmail() == null || invitation.getContactEmail().isEmpty()) {
                throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Contact email is missing in the invitation");
            }

            Contact contactToSave = new Contact();
            contactToSave.setUserId(userConnected.get());
            contactToSave.setContactEmail(invitation.getContactEmail());
            contactToSave.setContactName(invitation.getContactName());

            contactRepository.save(contactToSave);

        } catch (Exception e) {
            throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Error when saving contact: " + e.getMessage());
        }
    }


    @Override
    public void refuseInvitation(InvitationDTO invitationDTO) {

    }


}
