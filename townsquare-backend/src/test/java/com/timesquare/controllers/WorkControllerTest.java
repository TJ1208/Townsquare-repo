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
import com.timesquare.models.Work;
import com.timesquare.models.User;
import com.timesquare.repos.WorkRepository;
import com.timesquare.repos.UserRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private Work workplace;
	
	ObjectMapper mapper;
	
	@Autowired
	private WorkRepository workplaceRepo;
	
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
		
		workplaceRepo.save(new Work(1L, "HCL", "Junior Developer", "Cary", null, null, null, user1));
		workplaceRepo.save(new Work(2L, "Foodlion", "Grocery Manager", "Fuquay-Varina", null, null, null, user2));
	
		workplace = new Work(2L, "Foodlion", "Grocery Manager", "Fuquay-Varina", null, null, null, user3);
	}
	
	@Test
	void getAllWorkplaces() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/work")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAllUserWorkplaces() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/work/{userId}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getWorkById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/work/id/{workplaceId}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void addWorkplace() throws Exception {
		MvcResult result = mockMvc.perform(post("/api/work/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(workplace)))
				.andExpect(status().isCreated())
				.andReturn();
		
		assertEquals(workplace.getUser().getFirstName() + " " + workplace.getUser().getLastName()
				+ " now works at " + workplace.getCompany() + "!", result.getResponse().getContentAsString());
	}
	
	@Test
	void updateWorkplace() throws Exception {
		MvcResult result = mockMvc.perform(put("/api/work/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(workplace)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Workplace details for " + workplace.getCompany() + " have"
				+ " been updated.", result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteWorkplace() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/work/delete/{workplaceId}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(workplace)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Workplace deleted with id " + 2, result.getResponse().getContentAsString());
	}
}
