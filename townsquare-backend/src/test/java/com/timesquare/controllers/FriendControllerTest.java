package com.timesquare.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.timesquare.models.Friend;
import com.timesquare.models.FriendId;
import com.timesquare.models.User;
import com.timesquare.repos.FriendRepository;
import com.timesquare.repos.UserRepository;
import com.timesquare.services.FriendService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class FriendControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private Friend friend;
	
	ObjectMapper mapper;
	
	@Autowired
	private FriendRepository friendRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FriendService friendService;
	
	@BeforeEach
	void setUp() {
		
		mapper = new ObjectMapper();
		
		User friender = userRepo.save(new User(1, "Janice", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null, null, null, null, null, null, null,
				null, null, null));
		
		User receiver = userRepo.save(new User(2, "Taylor", "Joostema", "TaylorJ1208", "12345", "http",
				"TaylorJ1208@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null, null));
		
		User receiver2 = userRepo.save(new User(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null, null));
		
		friendRepo.save(new Friend(new FriendId(2L, 2L), receiver, receiver, false));
		friendRepo.save(new Friend(new FriendId(2L, 1L), receiver, friender, false));
	
		friend = new Friend(new FriendId(3L, 2L), receiver2, receiver, false);
	}
	
	@Test
	void getAllFriends() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/friend")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAllUserFriends() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/friend/{userId}", 3)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getFriendById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/friend/{userId}/{friendId}", 2, 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getFriendByIdError() throws Exception {
		Exception thrown = assertThrows(
				Exception.class,
				() -> friendService.getFriendById(5L, 5L),
				"User with the id " + 5 + ", does not"
						+ " have a friend with id " + 5);
		assertTrue(thrown.getMessage().contains("User with the id " + 5 + ", does not"
				+ " have a friend with id " + 5));
	}

	@Test
	void sendFriend() throws Exception {
		MvcResult result = mockMvc.perform(post("/api/friend/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(friend)))
				.andExpect(status().isCreated())
				.andReturn();
		
		assertEquals(friend.getFriend().getFirstName() + " " + friend.getFriend().getLastName()
				+ " is now your friend!", result.getResponse().getContentAsString());
	}
	
	@Test
	void updateFriend() throws Exception {
		User receiver = userRepo.save(new User(2, "Taylor", "Joostema", "TaylorJ1208", "12345", "http",
				"TaylorJ1208@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null, null));
		
		User friender = userRepo.save(new User(1, "Janice", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null, null, null, null, null, null, null,
				null, null, null));
		
		friend = new Friend(new FriendId(2L, 1L), receiver, friender, false);
		MvcResult result = mockMvc.perform(put("/api/friend/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(friend)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Your friend, " + friend.getUser().getFirstName() + " " 
				+ friend.getUser().getLastName() + " has been updated.",
				result.getResponse().getContentAsString());
	}
	
	@Test
	void updateFriendError() throws Exception {
		User receiver = userRepo.save(new User(2, "Taylor", "Joostema", "TaylorJ1208", "12345", "http",
				"TaylorJ1208@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null, null));
		
		User friender = userRepo.save(new User(1, "Janice", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null, null, null, null, null, null, null,
				null, null, null));
		friend = new Friend(new FriendId(3L, 4L), receiver, friender, false);
		MvcResult result = mockMvc.perform(put("/api/friend/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(friend)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("No friend found with the name " +
				friend.getUser().getFirstName() + " " + friend.getUser().getLastName(),
				result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteFriend() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/friend/delete/{userId}/{friendId}", 2, 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(friend)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Your friend has been removed.",
				result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteFriendError() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/friend/delete/{userId}/{friendId}", 5, 5)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(friend)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("No friend exists with id " + 5,
				result.getResponse().getContentAsString());
	}
}