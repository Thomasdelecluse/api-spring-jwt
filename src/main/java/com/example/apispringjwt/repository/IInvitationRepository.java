package com.example.apispringjwt.repository;

import com.example.apispringjwt.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvitationRepository extends JpaRepository<Invitation, Integer> {
}
