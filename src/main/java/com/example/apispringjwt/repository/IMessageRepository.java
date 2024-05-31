package com.example.apispringjwt.repository;

import com.example.apispringjwt.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByAuthor(String author);

}
