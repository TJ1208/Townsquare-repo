package com.timesquare.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.timesquare.dto.RequestDTO;
import com.timesquare.models.Request;
import com.timesquare.services.RequestService;

@RestController
@RequestMapping("/api/request")
public class RequestController {

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public List<Request> getAllRequests() {
		return requestService.getAllRequests();
	}
	
	@GetMapping("/{userId}")
	public List<Request> getAllUserRequests(@PathVariable Long userId) {
		return requestService.getAllUserRequests(userId);
	}
	
	@GetMapping("/sent/{requesterId}")
	public List<Request> getAllUserSentRequests(@PathVariable Long requesterId) {
		return requestService.getAllUserSentRequests(requesterId);
	}
	
	@GetMapping("/{receiverId}/{requesterId}")
	public Optional<Request> getRequestById(@PathVariable Long receiverId,
			@PathVariable Long requesterId) throws Exception {
		return requestService.getRequestById(receiverId, requesterId);
	}
	
	@PostMapping("/send")
	@ResponseStatus(HttpStatus.CREATED)
	public void sendRequest(@RequestBody RequestDTO requestDTO) throws ParseException {
		Request request = dtoToEntity(requestDTO);
		requestService.sendRequest(request);
	}
	
	@PutMapping("/update")
	public String updateRequest(@RequestBody RequestDTO requestDTO) throws ParseException {
		Request request = dtoToEntity(requestDTO);
		return requestService.updateRequest(request);
	}
	
	@DeleteMapping("/delete/{receiverId}/{requesterId}")
	public void deleteRequest(@PathVariable Long receiverId,
			@PathVariable Long requesterId) {
		requestService.deleteRequest(requesterId, receiverId);
	}
	
	private Request dtoToEntity(RequestDTO requestDTO) throws ParseException {
		Request request = modelMapper.map(requestDTO, Request.class);
		return request;
	}
	
}
