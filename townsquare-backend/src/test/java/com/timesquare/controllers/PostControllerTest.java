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
import com.timesquare.models.Post;
import com.timesquare.models.User;
import com.timesquare.repos.PostRepository;
import com.timesquare.repos.UserRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private Post post;
	
	ObjectMapper mapper;
	
	@Autowired
	private PostRepository postRepo;
	
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
		
		postRepo.save(new Post(1L, "Best place ever!", "It really is!", 1L, 2L, 3L, "http", user1, null));
		postRepo.save(new Post(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", user2, null));
	
		post = new Post(2L, "Not such a good place...", "It just stinked all the time :(", 1L, 2L, 3L, "http", user3, null);
	}
	
	@Test
	void getAllPosts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/post")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAllUserPosts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/post/{userId}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getPostById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/post/id/{postId}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void addPost() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("/api/post/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(post)))
				.andExpect(status().isCreated())
				.andReturn();
		
		assertEquals(post.getUser().getFirstName() + " " + post.getUser().getLastName()
				+ " just posted!", result.getResponse().getContentAsString());
	}
	
	@Test
	void updatePost() throws Exception {
		MvcResult result = mockMvc.perform(put("/api/post/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(post)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals(post.getUser().getFirstName() + " " + post.getUser().getLastName()
				+ "'s post has been updated.", result.getResponse().getContentAsString());
	}
	
	@Test
	void deletePost() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/post/delete/{postId}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(post)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Post deleted with id " + 2, result.getResponse().getContentAsString());
	}
}