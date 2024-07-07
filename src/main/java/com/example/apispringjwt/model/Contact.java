package com.example.apispringjwt.model;

import com.example.apispringjwt.exeption.ResponseEntityException;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;

import java.sql.Blob;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User userId;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Column(name = "contactName", nullable = false)
    private String contactName;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    private static final int MAX_IMAGE_SIZE = 100 * 1024 * 1024;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        if (image != null && image.length > MAX_IMAGE_SIZE) {
            throw new ResponseEntityException(HttpStatus.INTERNAL_SERVER_ERROR, "Image is too large to fit into memory");
        }
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

}
