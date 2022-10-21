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
import com.timesquare.models.Request;
import com.timesquare.models.RequestId;
import com.timesquare.models.User;
import com.timesquare.repos.RequestRepository;
import com.timesquare.repos.UserRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class RequestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private Request request;
	
	ObjectMapper mapper;
	
	@Autowired
	private RequestRepository requestRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@BeforeEach
	void setUp() {
		
		mapper = new ObjectMapper();
		
		User requester = userRepo.save(new User(1, "Janice", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null, null, null, null, null, null, null,
				null, null));
		
		User receiver = userRepo.save(new User(2, "Taylor", "Joostema", "TaylorJ1208", "12345", "http",
				"TaylorJ1208@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null));
		
		User receiver2 = userRepo.save(new User(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null));
		
		requestRepo.save(new Request(new RequestId(2, 2), receiver, receiver));
		requestRepo.save(new Request(new RequestId(2, 1), receiver, requester));
	
		request = new Request(new RequestId(3, 2), receiver2, receiver);
	}
	
	@Test
	void getAllRequests() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/request")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAllUserRequests() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/request/{receiverId}", 3L)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getRequestById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/request/{receiverId}/{requesterId}", 2, 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void sendRequest() throws Exception {
		MvcResult result = mockMvc.perform(post("/api/request/send")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isCreated())
				.andReturn();
		
		assertEquals("Your friend request to " + request.getReceiver().getFirstName()
				+ " " + request.getReceiver().getLastName() + " has been sent!",
				result.getResponse().getContentAsString());
	}
	
	@Test
	void updateRequest() throws Exception {
		User receiver = userRepo.save(new User(2, "Taylor", "Joostema", "TaylorJ1208", "12345", "http",
				"TaylorJ1208@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null));
		
		User requester = userRepo.save(new User(1, "Janice", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null, null, null, null, null, null, null,
				null, null));
		
		request = new Request(new RequestId(2, 1), receiver, requester);
		MvcResult result = mockMvc.perform(put("/api/request/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Your friend request to " + request.getReceiver().getFirstName() + " "
				+ request.getReceiver().getLastName() + " has been updated.",
				result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteRequest() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/request/delete/{receiverId}/{requesterId}", 2, 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Friend request with id " + 2 + " has been removed.",
				result.getResponse().getContentAsString());
	}
}
