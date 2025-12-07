package com.portfolio.service;
import java.util.List;
import com.portfolio.model.Contact;

public interface ContactService {
	public Contact getContactById(long id);
    public Contact saveContactMessage(Contact contact);
    public List<Contact> getAllContactMessages();
    public boolean deleteById(Long contactId);
}
