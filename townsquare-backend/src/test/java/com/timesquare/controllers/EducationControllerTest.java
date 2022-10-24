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
import com.timesquare.models.Education;
import com.timesquare.models.User;
import com.timesquare.repos.EducationRepository;
import com.timesquare.repos.UserRepository;
import com.timesquare.services.EducationService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class EducationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private Education education;
	
	ObjectMapper mapper;
	
	@Autowired
	private EducationRepository educationRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EducationService educationService;
	
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
		
		educationRepo.save(new Education(1L, false, true, null, null, "Currently working towards my high school diploma",
				"Fuquay-Varina Senior High School", "High School Diploma", user1));
		educationRepo.save(new Education(2L, false, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
				"Fuquay-Varina Senior High School", "High School Diploma", user2));
	
		education = new Education(2L, false, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
				"Fuquay-Varina Senior High School", "High School Diploma", user3);
	}
	
	@Test
	void getAllEducations() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/education")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAllUserEducations() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/education/{userId}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getEducationById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/education/id/{educationId}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getEducationByIdError() throws Exception {
		Exception thrown = assertThrows(
				Exception.class,
				() -> educationService.getEducationById(5L),
				"User education not found with id " + 5);
		assertTrue(thrown.getMessage().contains("User education not found with id " + 5));
	}

	@Test
	void sendEducation() throws Exception {
		MvcResult result = mockMvc.perform(post("/api/education/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(education)))
				.andExpect(status().isCreated())
				.andReturn();
		
		assertEquals("New education for " + education.getUser().getFirstName() +
				" " + education.getUser().getLastName() + " has been added.",
				result.getResponse().getContentAsString());
	}
	
	@Test
	void updateEducation() throws Exception {
		MvcResult result = mockMvc.perform(put("/api/education/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(education)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Education for " + education.getUser().getFirstName() +
				" " + education.getUser().getLastName() + " has been updated.", 
				result.getResponse().getContentAsString());
	}
	
	@Test
	void updateEducationError() throws Exception {
		User user3 = userRepo.save(new User(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null));
		Education newEducation = new Education(3L, false, true, new Date(55), new Date(65), "Graduated with a High School Diploma", 
				"Fuquay-Varina Senior High School", "High School Diploma", user3);
		MvcResult result = mockMvc.perform(put("/api/education/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newEducation)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Education with id " + newEducation.getEducationId() 
				+ " for " + newEducation.getUser().getFirstName() +
				" " + newEducation.getUser().getLastName() + " was not found.", 
				result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteEducation() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/education/delete/{educationId}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(education)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Education deleted successfully.", result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteEducationError() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/education/delete/{educationId}", 5)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(education)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Education not found with id " + 5
				, result.getResponse().getContentAsString());
	}
}