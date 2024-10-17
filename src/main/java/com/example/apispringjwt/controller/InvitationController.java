package com.example.apispringjwt.controller;

import com.example.apispringjwt.dto.request.InvitationDTO;
import com.example.apispringjwt.service.IInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/invitation")
public class InvitationController {
    @Autowired
    IInvitationService invitationService;

    @PostMapping()
    private ResponseEntity<?> addInvitation(@RequestBody InvitationDTO invitationDTO) {
        invitationService.addInvitation(invitationDTO);
        return ResponseEntity.ok().build();
    };

    @PostMapping("/{invitationId}")
    private ResponseEntity<?> acceptInvitation(@PathVariable  int invitationId)  {
        invitationService.acceptInvitation(invitationId);
        return ResponseEntity.ok().build();
    };


  // @PostMapping()
  // private ResponseEntity<?> refuseContact(@RequestBody InvitationDTO invitationDTO) {
  //     invitationService.refuseInvitation(invitationDTO);
  //     return ResponseEntity.ok().build();
  // };

}
