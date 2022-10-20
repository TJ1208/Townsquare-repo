package com.timesquare.controllers;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/{receiverId}")
	public List<Request> getAllUserRequests(@PathVariable Long receiverId) {
		return requestService.getAllUserRequests(receiverId);
	}
	
	@GetMapping("/{receiverId}/{requesterId}")
	public Request getRequestById(@PathVariable Long receiverId,
			@PathVariable Long requesterId) throws Exception {
		return requestService.getRequestById(receiverId, requesterId);
	}
	
	@PostMapping("/send")
	public String sendRequest(@RequestBody RequestDTO requestDTO) throws ParseException {
		Request request = dtoToEntity(requestDTO);
		return requestService.sendRequest(request);
	}
	
	@PutMapping("/update")
	public String updateRequest(@RequestBody RequestDTO requestDTO) throws ParseException {
		Request request = dtoToEntity(requestDTO);
		return requestService.updateRequest(request);
	}
	
	@DeleteMapping("/delete/{receiverId}/{requesterId}")
	public String deleteRequest(@PathVariable Long receiverId,
			@PathVariable Long requesterId) {
		return requestService.deleteRequest(requesterId, receiverId);
	}
	
	private Request dtoToEntity(RequestDTO requestDTO) throws ParseException {
		Request request = modelMapper.map(requestDTO, Request.class);
		return request;
	}
	
}
