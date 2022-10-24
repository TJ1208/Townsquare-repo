package com.timesquare.controllers;

import java.util.List;

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

import com.timesquare.dto.EducationDTO;
import com.timesquare.models.Education;
import com.timesquare.services.EducationService;

@RestController
@RequestMapping("/api/education")
public class EducationController {
	
	@Autowired
	private EducationService educationService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public List<Education> getAllEducations() {
		return educationService.getAllEducations();
	}
	
	@GetMapping("/{userId}")
	public List<Education> getAllUserEducations(@PathVariable Long userId) {
		return educationService.getAllUserEducations(userId);
	}
	
	@GetMapping("/id/{educationId}")
	public Education getEducationById(@PathVariable Long educationId) throws Exception {
		return educationService.getEducationById(educationId);
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public String addEducation(@RequestBody EducationDTO educationDTO) {
		Education education = dtoToEntity(educationDTO);
		return educationService.addEducation(education);
	}
	
	@PutMapping("/update")
	public String updateEducation(@RequestBody EducationDTO educationDTO) {
		Education education = dtoToEntity(educationDTO);
		return educationService.updateEducation(education);
	}
	
	@DeleteMapping("/delete/{educationId}")
	public String deleteEducation(@PathVariable Long educationId) {
		return educationService.deleteEducation(educationId);
	}
	
	public Education dtoToEntity(EducationDTO educationDTO) {
		Education education = modelMapper.map(educationDTO, Education.class);
		return education;
	}

}
