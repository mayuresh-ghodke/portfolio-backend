
package com.portfolio.service.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.portfolio.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.model.Contact;
import com.portfolio.repository.ContactRepository;
import com.portfolio.service.ContactService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.username}")
    private String toEmail;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact saveContactMessage(Contact contactMessage) {
    	contactMessage.setCreatedAt(new Date());
        String subject = "PORTFOLIO WEBSITE MESSAGE - New Contact Message from " + contactMessage.getName();

        String body = "You have received a new message from your portfolio website.\n\n" +
                "Sender Name: " + contactMessage.getName() + "\n" +
                "Sender Email: " + contactMessage.getContactEmail() + "\n\n" +
                "Message:\n" + contactMessage.getContactMessage() + "\n\n" +
                "Please respond promptly.";

        emailService.sendEmail(toEmail, subject, body);
        return contactRepository.save(contactMessage);
    }

    @Override
    public List<Contact> getAllContactMessages() {
        List<Contact> contactList = new ArrayList<>();
        contactList = contactRepository.findAll();
        return contactList;
    }

    @Override
    public boolean deleteById(Long contactId) {
    	Contact contact = getContactById(contactId);
    	if(contact != null) {
            contactRepository.deleteById(contactId);
            return true;
    	}
        return false;
    }

	@Override
	public Contact getContactById(long id) {
		return contactRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Contact not found with ID-"+id));
	}
}
