package com.example.apispringjwt.service.impl;

import com.example.apispringjwt.dto.request.InvitationDTO;
import com.example.apispringjwt.exeption.ResponseEntityException;
import com.example.apispringjwt.model.Invitation;
import com.example.apispringjwt.model.User;
import com.example.apispringjwt.repository.IInvitationRepository;
import com.example.apispringjwt.repository.IUserRepository;
import com.example.apispringjwt.service.IInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.apispringjwt.service.impl.AuthService.getUserNameConnected;

@Service
public class InvitationService implements IInvitationService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IInvitationRepository invitationRepository;
    @Override
    public void addInvitation(InvitationDTO invitationDTO) {
        if (invitationDTO == null){
            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "No User found with your id");
        }
        try {
            String userEmail = getUserNameConnected();
            Optional<User> userConnected = userRepository.findByEmail(userEmail);

            Optional<User> userToAdd = userRepository.findByEmail(invitationDTO.contactEmail());
            if (userConnected.isPresent() && userToAdd.isPresent()){
                Invitation invitation = new Invitation(userConnected.get(), invitationDTO.contactEmail(), userToAdd.get().getName(), "pending");

                invitationRepository.save(invitation);
            }
        }catch (Exception e){
            //todo
        }
    }
}
