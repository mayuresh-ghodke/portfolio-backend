package com.portfolio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class ContactDto {

    private String name;
    private String contactEmail;
    private String contactMobile;
    private String contactSubject; 
    private String contactMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy HH:mm")
    private Date createdAt;

    // Constructor
    public ContactDto(String name, String contactEmail, String contactMobile,
                              String contactSubject, String contactMessage, Date createdAt) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactMobile = contactMobile;
        this.contactSubject = contactSubject;
        this.contactMessage = contactMessage;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactSubject() {
        return contactSubject;
    }

    public void setContactSubject(String contactSubject) {
        this.contactSubject = contactSubject;
    }

    public String getContactMessage() {
        return contactMessage;
    }

    public void setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
