package com.example.apispringjwt.service.impl;

import com.example.apispringjwt.dto.request.CreateMessageDTO;
import com.example.apispringjwt.exeption.ResponseEntityException;
import com.example.apispringjwt.model.Message;
import com.example.apispringjwt.model.User;
import com.example.apispringjwt.repository.IContactRepository;
import com.example.apispringjwt.repository.IMessageRepository;
import com.example.apispringjwt.repository.IUserRepository;
import com.example.apispringjwt.service.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.apispringjwt.service.impl.AuthService.getUserNameConnected;

@Service
public class MessageService implements IMessage {

    @Autowired
    private IMessageRepository messageRepository;

    @Autowired
    private IContactRepository iContactRepository;

    @Autowired
    private IUserRepository userRepository;

    private NotificationService notificationService;

    public MessageService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public List<Message> getConversationWithContact(String email) {
        if (email == null) {
            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "no content for this conversation");
        }
        String userEmailConnected = getUserNameConnected();
        List<Message> allMessagesReceiveByContact = messageRepository.findByAuthorAndDestination(email, userEmailConnected);
        List<Message> allMessagesSentToContact = messageRepository.findByAuthorAndDestination(userEmailConnected, email);
        List<Message> allMessages = new ArrayList<>();
        allMessages.addAll(allMessagesReceiveByContact);
        allMessages.addAll(allMessagesSentToContact);
        if (allMessages.isEmpty()) {
            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "No messages found");
        }

        return allMessages;
    }


    @Override
    public void saveMessage(CreateMessageDTO createMessageDTO) {
        if (createMessageDTO == null || createMessageDTO.message() == null) {
            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "no content found in request");
        }
        Optional<User> UserDestination = userRepository.findByEmail(createMessageDTO.contactEmail());
        if (!UserDestination.isPresent()) {
            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "User not found");
        }
        Message messageToSave = new Message();
        messageToSave.setAuthor(getUserNameConnected());
        messageToSave.setMessage(createMessageDTO.message());
        messageToSave.setUserEmailDestination(createMessageDTO.contactEmail());
        messageToSave.setDate(LocalDateTime.now());
        messageRepository.save(messageToSave);
        notificationService.notifyUser(UserDestination.get(), "refresh");
    }

    @Override
    public List<Message> getAllMessage() {
        List<Message> allMessages = messageRepository.findAll();
        if (allMessages.isEmpty()) {
            throw new ResponseEntityException(HttpStatus.NO_CONTENT, "no messages found");
        }
        return allMessages;
    }


}
