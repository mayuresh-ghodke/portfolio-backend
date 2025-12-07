package com.portfolio.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.dto.ContactDto;
import com.portfolio.model.Contact;
import com.portfolio.repository.ContactRepository;
import com.portfolio.service.ContactService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {
    private final ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;

    @GetMapping("/admin/view-contacts")
    public ResponseEntity<List<ContactDto>> displayAllContactMessages(){
        List<Contact> contactList = contactService.getAllContactMessages();
        List<ContactDto> responseList = contactList.stream().map(contact -> new ContactDto( contact.getName(), contact.getContactEmail(), contact.getContactMobile(), contact.getContactSubject(), contact.getContactMessage(), contact.getCreatedAt() )) .toList();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete-contact/{contactId}")
    public String deleteContactMessageById(@PathVariable Long contactId){

        boolean flag = contactService.deleteById(contactId);
        String result = "Deletion Failed";
        if(flag){
            result = "Deleted Successfully";
        }
        return result;
    }
}
