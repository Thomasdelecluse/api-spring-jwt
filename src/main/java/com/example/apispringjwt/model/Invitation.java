package com.example.apispringjwt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "INVITATION")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private User senderId;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "status", nullable = false)
    private String status;

    public Invitation(User senderId, String contactEmail, String contactName, String status) {
        this.senderId = senderId;
        this.contactEmail = contactEmail;
        this.contactName = contactName;
        this.status = status;
    }

    public Invitation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSenderId() {
        return senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

