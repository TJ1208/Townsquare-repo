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
import com.timesquare.models.Image;
import com.timesquare.models.User;
import com.timesquare.repos.ImageRepository;
import com.timesquare.repos.UserRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class ImageControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private Image image;
	
	ObjectMapper mapper;
	
	@Autowired
	private ImageRepository imageRepo;
	
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
		
		imageRepo.save(new Image(1L, "http", new java.sql.Date(55), user1));
		imageRepo.save(new Image(2L, "http", new java.sql.Date(55), user2));
	
		image = new Image(2L, "http", new java.sql.Date(55), user3);
	}
	
	@Test
	void getAllImages() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/image")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAllUserImages() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/image/{userId}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getImageById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/image/id/{imageId}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void sendImage() throws Exception {
		MvcResult result = mockMvc.perform(post("/api/image/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(image)))
				.andExpect(status().isCreated())
				.andReturn();
		
		assertEquals("Image saved successfully",
				result.getResponse().getContentAsString());
	}
	
	@Test
	void updateImage() throws Exception {
		MvcResult result = mockMvc.perform(put("/api/image/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(image)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Image updated successfully", result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteImage() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/image/delete/{imageId}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(image)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Image deleted successfully", result.getResponse().getContentAsString());
	}
}
