package com.timesquare.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timesquare.models.Contact;
import com.timesquare.models.User;
import com.timesquare.repos.ContactRepository;
import com.timesquare.repos.UserRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private Contact contact;
	
	ObjectMapper mapper;
	
	@Autowired
	private ContactRepository contactRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@BeforeEach
	void setUp() {
		
		mapper = new ObjectMapper();
		
		User user1 = userRepo.save(new User(1, "Janice", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null, null, null, null, null, null, null,
				null, null));
		
		User user2 = userRepo.save(new User(2, "Taylor", "Joostema", "TaylorJ1208", "12345", "http",
				"TaylorJ1208@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null));
		
		User user3 = userRepo.save(new User(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null));
		
		contactRepo.save(new Contact(1L, "123-456-7890", "098-765-4321", "919-339-3801", user1));
		contactRepo.save(new Contact(2L, "123-456-7890", "098-765-4321", "919-339-3801", user2));
	
		contact = new Contact(2L, "123-456-7890", "098-765-4321", "919-339-3801", user3);
	}
	
	@Test
	void getAllContacts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/contact")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAllUserContacts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/contact/{userId}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getContactById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/contact/id/{contactId}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void addContact() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("/api/contact/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(contact)))
				.andExpect(status().isCreated())
				.andReturn();
		
		assertEquals("New contact for " + contact.getUser().getFirstName() +
				" " + contact.getUser().getLastName() + " has been added.", result.getResponse().getContentAsString());
	}
	
	@Test
	void updateContact() throws Exception {
		MvcResult result = mockMvc.perform(put("/api/contact/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(contact)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Contact for " + contact.getUser().getFirstName() +
				" " + contact.getUser().getLastName() + " has been updated.", result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteContact() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/contact/delete/{contactId}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(contact)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Contact deleted successfully.", result.getResponse().getContentAsString());
	}
}