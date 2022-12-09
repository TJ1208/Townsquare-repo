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
import com.timesquare.models.Address;
import com.timesquare.models.User;
import com.timesquare.repos.AddressRepository;
import com.timesquare.repos.UserRepository;
import com.timesquare.services.AddressService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	private Address address;
	
	ObjectMapper mapper;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressService addressService;
	
	@BeforeEach
	void setUp() {
		
		mapper = new ObjectMapper();
		
		User user1 = userRepo.save(new User(1, "Janice", "Doe", "Janice", "67890", "http",
				"Janice@example.com", "Family, friends, and football!", "http",
				new Date(65), "Oxford, NC",
				"Raleigh, NC", null, null, null, null, null, null, null,
				null, null, null));
		
		User user2 = userRepo.save(new User(2, "Taylor", "Joostema", "TaylorJ1208", "12345", "http",
				"TaylorJ1208@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null, null));
		
		User user3 = userRepo.save(new User(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null, null));
		
		addressRepo.save(new Address(1L, "Fuquay-Varina", "NC", "913 Bridlemine Dr.", "27592",
				"USA", null, user1));
		addressRepo.save(new Address(2L, "Willow Springs", "NC", "1543 Middle Ridge Dr.", "27545",
				"USA", null, user2));
		
		address = new Address(2L, "Willow Springs", "NC", "1543 Middle Ridge Dr.", "27545",
				"USA", null, user3);
	}
	
	@Test
	void getAllAddresss() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/address")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAllUserAddresss() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/address/{userId}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAddressById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/address/id/{addressId}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	void getAddressByIdError() throws Exception {
		Exception thrown = assertThrows(
				Exception.class,
				() -> addressService.getAddressById(5L),
				"Address not found with id: " + 5);
		assertTrue(thrown.getMessage().contains("Address not found with id: " + 5));
	}

	@Test
	void addAddress() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post("/api/address/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(address)))
				.andExpect(status().isCreated())
				.andReturn();
		
		assertEquals(address.getUser().getFirstName() + address.getUser().getLastName() +
				"'s address has been added.", result.getResponse().getContentAsString());
	}
	
	@Test
	void updateAddress() throws Exception {
		MvcResult result = mockMvc.perform(put("/api/address/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(address)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals(address.getUser().getFirstName() + address.getUser().getLastName()
				+ "'s address has been updated.", result.getResponse().getContentAsString());
	}
	
	@Test
	void updateAddressError() throws Exception {
		User user3 = userRepo.save(new User(3, "Taylor", "Joostema", "TaylorJ", "12345", "http",
				"TaylorJ@example.com", "What a beautiful world!", "http",
				new Date(55), "Raleigh, NC",
				"Cary, NC", null, null, null, null, null, null, null,
				null, null, null));
		Address newAddress = new Address(3L, "Willow Springs", "NC", "1543 Middle Ridge Dr.", "27545",
				"USA", null, user3);
		
		MvcResult result = mockMvc.perform(put("/api/address/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(newAddress)))
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals(newAddress.getUser().getFirstName() + newAddress.getUser().getLastName() +
				"'s address has not been found with id: " 
				+ newAddress.getAddressId(), result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteAddress() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/address/delete/{addressId}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(address)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Users address has been deleted.", result.getResponse().getContentAsString());
	}
	
	@Test
	void deleteAddressError() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/address/delete/{addressId}", 5)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(address)))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		assertEquals("Could not find address with id: " + 5, result.getResponse().getContentAsString());
	}
}