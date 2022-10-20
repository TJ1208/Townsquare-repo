package com.timesquare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesquare.models.Contact;
import com.timesquare.repos.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepo;
	
	public List<Contact> getAllContacts() {
		return contactRepo.findAll();
	}
	
	public List<Contact> getAllUserContacts(Long userId) {
		return contactRepo.findAll().stream()
					.filter((contact) -> contact.getUser().getUserId() == userId)
					.collect(Collectors.toList());
	}
	
	public Contact getContactById(Long contactId) throws Exception {
		return contactRepo.findById(contactId).orElseThrow(
				() -> new Exception("User contact not found" +
						" with id " + contactId));
	}
	
	public String addContact(Contact contact) {
		contactRepo.save(contact);
		return "New contact for " + contact.getUser().getFirstName() +
				" " + contact.getUser().getLastName() + " has been added.";
	}
	
	public String updateContact(Contact contact) {
		if (contactRepo.findById(contact.getContactId()).isPresent()) {
			contactRepo.save(contact);
			return "Contact for " + contact.getUser().getFirstName() +
					" " + contact.getUser().getLastName() + " has been updated.";
		}
		return "Contact with id " + contact.getContactId() 
				+ " for " + contact.getUser().getFirstName() +
				" " + contact.getUser().getLastName() + " was not found.";
	}
	
	public String deleteContact(Long contactId) {
		if (contactRepo.findById(contactId).isPresent()) {
			contactRepo.deleteById(contactId);
			return "Contact deleted successfully.";
		}
		return "Contact not found with id " + contactId;
		
	}
}
