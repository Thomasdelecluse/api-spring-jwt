package com.example.apispringjwt.repository;

import com.example.apispringjwt.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageRepository extends JpaRepository<Message, Integer> {
}
