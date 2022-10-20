package com.timesquare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesquare.models.Education;
import com.timesquare.repos.EducationRepository;

@Service
public class EducationService {
	
	@Autowired
	private EducationRepository educationRepo;
	
	public List<Education> getAllEducations() {
		return educationRepo.findAll();
	}
	
	public List<Education> getAllUserEducations(Long userId) {
		return educationRepo.findAll().stream()
					.filter((education) -> education.getUser().getUserId() == userId)
					.collect(Collectors.toList());
	}
	
	public Education getEducationById(Long educationId) throws Exception {
		return educationRepo.findById(educationId).orElseThrow(
				() -> new Exception("User education not found" +
						" with id " + educationId));
	}
	
	public String addEducation(Education education) {
		educationRepo.save(education);
		return "New education for " + education.getUser().getFirstName() +
				" " + education.getUser().getLastName() + " has been added.";
	}
	
	public String updateEducation(Education education) {
		if (educationRepo.findById(education.getEducationId()).isPresent()) {
			educationRepo.save(education);
			return "Education for " + education.getUser().getFirstName() +
					" " + education.getUser().getLastName() + " has been updated.";
		}
		return "Education with id " + education.getEducationId() 
				+ " for " + education.getUser().getFirstName() +
				" " + education.getUser().getLastName() + " was not found.";
	}
	
	public String deleteEducation(Long educationId) {
		if (educationRepo.findById(educationId).isPresent()) {
			educationRepo.deleteById(educationId);
			return "Education deleted successfully.";
		}
		return "Education not found with id " + educationId;
		
	}
}
