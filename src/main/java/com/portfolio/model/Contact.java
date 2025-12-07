package com.portfolio.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contact_me")
public class Contact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contactId;
    private String name; 
    private String contactEmail;
    private String contactMobile;
    private String contactSubject;
    private String contactMessage;

    private Date createdAt;

    public Contact() {}

	public Contact(Long contactId, String name, String contactEmail, String contactMobile, String contactSubject,
			String contactMessage, Date createdAt) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.contactEmail = contactEmail;
		this.contactMobile = contactMobile;
		this.contactSubject = contactSubject;
		this.contactMessage = contactMessage;
		this.createdAt = createdAt;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

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
