package com.example.apispringjwt.repository;

import com.example.apispringjwt.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByAuthor(String author);

    @Query("SELECT m FROM Message m WHERE m.author = :author AND m.destination = :destination")
    List<Message> findByAuthorAndDestination(@Param("author") String author, @Param("destination") String destination);
}
