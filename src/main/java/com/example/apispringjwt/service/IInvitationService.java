package com.example.apispringjwt.service;

import com.example.apispringjwt.dto.request.InvitationDTO;

public interface IInvitationService {
    void addInvitation(InvitationDTO invitationDTO);
    void acceptInvitation(int id);
    void refuseInvitation(InvitationDTO invitationDTO);

}
