package com.timesquare.controllers;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.timesquare.dto.ContactDTO;
import com.timesquare.models.Contact;
import com.timesquare.services.ContactService;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public List<Contact> getAllContacts() {
		return contactService.getAllContacts();
	}
	
	@GetMapping("/{userId}")
	public List<Contact> getAllUserContacts(@PathVariable Long userId) {
		return contactService.getAllUserContacts(userId);
	}
	
	@GetMapping("/id/{contactId}")
	public Contact getContactById(@PathVariable Long contactId) throws Exception {
		return contactService.getContactById(contactId);
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public String addContact(@RequestBody ContactDTO contactDTO) throws ParseException {
		Contact contact = dtoToEntity(contactDTO);
		return contactService.addContact(contact);
	}
	
	@PutMapping("/update")
	public String updateContact(@RequestBody ContactDTO contactDTO) throws ParseException {
		Contact contact = dtoToEntity(contactDTO);
		return contactService.updateContact(contact);
	}
	
	@DeleteMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable Long contactId) {
		return contactService.deleteContact(contactId);
	}
	
	private Contact dtoToEntity(ContactDTO contactDTO) throws ParseException {
		Contact contact = modelMapper.map(contactDTO, Contact.class);
		return contact;
	}
}
