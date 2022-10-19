package com.timesquare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timesquare.models.Request;
import com.timesquare.services.RequestService;

@RestController
@RequestMapping("/api/request")
public class RequestController {

	@Autowired
	private RequestService requestService;
	
	@GetMapping("/{receiverId}")
	public List<Request> getAllRequests(@PathVariable Long receiverId) {
		return requestService.getAllRequests(receiverId);
	}
	
	@GetMapping("/{receiverId}/{requesterId}")
	public Request getRequestById(@PathVariable Long receiverId,
			@PathVariable Long requesterId) throws Exception {
		return requestService.getRequestById(receiverId, requesterId);
	}
	
	@PostMapping("/send")
	public String sendRequest(@RequestBody Request request) {
		return requestService.sendRequest(request);
	}
	
	@PutMapping("/update")
	public String updateRequest(@RequestBody Request request) {
		return requestService.updateRequest(request);
	}
	
	@DeleteMapping("/delete/{receiverId}/{requestId}")
	public String deleteRequest(@PathVariable Long receiverId,
			@PathVariable Long requesterId) {
		return requestService.deleteRequest(requesterId, receiverId);
	}
}
