package com.timesquare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesquare.models.Work;
import com.timesquare.repos.WorkRepository;

@Service
public class WorkService {

	@Autowired
	private WorkRepository workRepo;
	
	public List<Work> getAllWorkplaces() {
		return workRepo.findAll();
	}
	
	public List<Work> getAllUserWorkplaces(Long userId) {
		return workRepo.findAll().stream()
					.filter((workplace) -> workplace.getUser().getUserId() == userId)
					.collect(Collectors.toList());
	}
	
	public Work getWorkplaceById(Long workplaceId) throws Exception {
		return workRepo.findById(workplaceId).orElseThrow(
				() -> new Exception("Workplace not found with id " + workplaceId));
	}
	
	public String addWorkplace(Work workplace) {
		workRepo.save(workplace);
		return workplace.getUser().getFirstName() + " " + workplace.getUser().getLastName()
				+ " now works at " + workplace.getCompany() + "!";
	}
	
	public String updateWorkplace(Work workplace) {
		if (workRepo.findById(workplace.getWorkplaceId()).isPresent()) {
			workRepo.save(workplace);
			return "Workplace details for " + workplace.getCompany() + " have"
					+ " been updated.";
		}
		return "No workplace details found for the company " + workplace.getCompany();
	}
	
	public String deleteWorkplace(Long workplaceId) {
		if (workRepo.findById(workplaceId).isPresent()) {
			workRepo.deleteById(workplaceId);
			return "Workplace deleted with id " + workplaceId;
		}
		return "No workplace found with id " + workplaceId;
	}
}
