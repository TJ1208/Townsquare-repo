package com.timesquare.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.timesquare.models.User;
import com.timesquare.repos.UserRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private User user;
	
	ObjectMapper mapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@BeforeEach
	void setUp() {
		userRepo.save(new User(1L, "John", "Doe", "JohnnyD", "12345", "http",
				"JohnDoe@example.com", "Living my best life!", "http",
				new Date(55), "Raleigh, NC",
				"Oxford, NC", null, null, null, null, null, null, null,
				null, null));
		userRepo.save(new User(2L, "Janice", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null, null, null, null, null, null, null,
				null, null));
		mapper = new ObjectMapper();
		
		user = new User(3L, "Taylor", "Joostema", "TaylorJ1208", "12345", "http",
				"TaylorJ1208@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null);
	}
	
	@Test
	void getAllUsers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/user")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getUserById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/user/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getUserByUsername() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/user/name/{username}", "Taylor")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void registerUser() throws Exception {
		MvcResult result = mockMvc.perform(post("/api/user/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user)))
				.andExpect(status().isCreated())
				.andReturn();
		
		assertEquals(user.getFirstName() + " " + user.getLastName() +
				"'s acount is now registered!",
				result.getResponse().getContentAsString());
	}
	
	@Test
	void updateUser() throws Exception {
		User updatedUser = new User(2L, "Taylor", "Joostema", "TaylorJ1208", "12345", "http",
				"TaylorJ1208@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null);
		MvcResult result = mockMvc.perform(put("/api/user/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(updatedUser)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals(user.getFirstName() + " " + user.getLastName() + 
				"'s account has been updated.",
				result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteUser() throws Exception {
		mockMvc.perform(delete("/api/user/delete/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user)))
				.andDo(print())
				.andExpect(status().isOk());
		
		assertEquals(1, userRepo.count());
			
	}

}
