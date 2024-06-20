package com.example.apispringjwt.repository;

import com.example.apispringjwt.model.Contact;
import com.example.apispringjwt.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByUserId_Id(int id);

}
