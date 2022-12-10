package com.timesquare.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesquare.models.Request;
import com.timesquare.models.RequestId;
import com.timesquare.repos.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepo;
	
	public List<Request> getAllRequests() {
		return requestRepo.findAll();
	}
	
	public List<Request> getAllUserRequests(Long receiverId) {
		return requestRepo.findAll().stream()
				.filter((request) -> request.getReceiver().getUserId() == receiverId)
				.collect(Collectors.toList());
	}
	
	public List<Request> getAllUserSentRequests(Long requesterId) {
		return requestRepo.findAll().stream()
				.filter((request) -> request.getRequester().getUserId() == requesterId)
				.collect(Collectors.toList());
	}
	
	public Optional<Request> getRequestById(Long receiverId, Long requesterId) throws Exception {
		return requestRepo.findById(new RequestId(receiverId, requesterId));
	}
	
	public void sendRequest(Request request) {
		requestRepo.save(request);
	}
	
	public String updateRequest(Request request) {
		if (requestRepo.findById(new RequestId(request.getReceiver().getUserId()
				, request.getRequester().getUserId())).isPresent()) {
			requestRepo.save(request);
			return "Your friend request to " +
					request.getReceiver().getFirstName() + " "
					+ request.getReceiver().getLastName() +
					" has been updated.";
		}
		return "No friend request to " + request.getReceiver().getFirstName() +
				" " + request.getReceiver().getLastName() + " from " +
				request.getRequester().getFirstName() + " " +
				request.getRequester().getLastName() + " was found.";
		
	}
	
	public void deleteRequest(Long requesterId, Long receiverId) {
		if (requestRepo.findById(new RequestId(requesterId, receiverId)).isPresent()) {
			requestRepo.deleteById(new RequestId(requesterId, receiverId));
		}
	}
}
