package com.timesquare.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.timesquare.models.Comment;
import com.timesquare.models.Post;
import com.timesquare.models.User;
import com.timesquare.repos.CommentRepository;
import com.timesquare.repos.PostRepository;
import com.timesquare.repos.UserRepository;
import com.timesquare.services.CommentService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private Comment comment;
	
	ObjectMapper mapper;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommentService commentService;
	
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
		
		Post post = new Post(1L, "Best place ever!", "It really is!", 1L, 2L, 3L, "http", user1, null);
		postRepo.save(post);
		
		commentRepo.save(new Comment(1L, "This is an amazing view!", new Date(85), 15L, 15L, post, user1));
		commentRepo.save(new Comment(2L, "The sky line is so pretty!", new Date(34), 110L, 15L, post, user2));
	
		comment = new Comment(2L, "Wish I was there!!", new Date(110), 64L, 4L, post, user3);
	}
	
	@Test
	void getAllComments() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/comment")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAllUserComments() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/comment/{userId}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getCommentById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/comment/id/{commentId}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getCommentByIdError() throws Exception {
		Exception thrown = assertThrows(
				Exception.class,
				() -> commentService.getCommentById(5L),
				"Comment not found with id " + 5);
		assertTrue(thrown.getMessage().contains("Comment not found with id " + 5));
	}

	@Test
	void addComment() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("/api/comment/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(comment)))
				.andExpect(status().isCreated())
				.andReturn();
		
		assertEquals(comment.getUser().getFirstName() + " " + comment.getUser().getLastName()
				+ " commented on your post!", result.getResponse().getContentAsString());
	}
	
	@Test
	void updateComment() throws Exception {
		MvcResult result = mockMvc.perform(put("/api/comment/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(comment)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals(comment.getUser().getFirstName() + " " + comment.getUser().getLastName()
				+ "'s comment was edited.", result.getResponse().getContentAsString());
	}
	
	@Test
	void updateCommentError() throws Exception {
		User user3 = userRepo.save(new User(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null));
		Post post = new Post(1L, "Best place ever!", "It really is!", 1L, 2L, 3L, "http", user3, null);
		Comment newComment = new Comment(2L, "Wish I was there!!", new Date(110), 64L, 4L, post, user3);
		MvcResult result = mockMvc.perform(put("/api/comment/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newComment)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals(newComment.getUser().getFirstName() + " " + newComment.getUser().getLastName()
				+ "'s comment was edited.", result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteComment() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/comment/delete/{commentId}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(comment)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Comment deleted with id " + 2, result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteCommentError() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/comment/delete/{commentId}", 5)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(comment)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("No comment found with id " + 5, result.getResponse().getContentAsString());
	}
}