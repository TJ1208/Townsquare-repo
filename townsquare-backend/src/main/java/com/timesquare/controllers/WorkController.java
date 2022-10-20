package com.timesquare.controllers;

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

import com.timesquare.dto.WorkDTO;
import com.timesquare.models.Work;
import com.timesquare.services.WorkService;

@RestController
@RequestMapping("/api/work")
public class WorkController {
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public List<Work> getAllWorkplaces() {
		return workService.getAllWorkplaces();
	}
	
	@GetMapping("/{userId}")
	public List<Work> getAllUserWorkplaces(@PathVariable Long userId) {
		return workService.getAllUserWorkplaces(userId);
	}

	@GetMapping("/id/{workplaceId}")
	public Work getWorkplaceById(@PathVariable Long workplaceId) throws Exception {
		return workService.getWorkplaceById(workplaceId);
	}
	
	@PostMapping("/add")
	public String addWorkplace(@RequestBody WorkDTO workplaceDTO) {
		Work work = dtoToEntity(workplaceDTO);
		return workService.addWorkplace(work);
	}
	
	@PutMapping("/update")
	public String updateWorkplace(@RequestBody WorkDTO workplaceDTO) {
		Work work = modelMapper.map(workplaceDTO, Work.class);
		return workService.updateWorkplace(work);
	}
	
	@DeleteMapping("/delete/{workplaceId}")
	public String deleteWorkplace(@PathVariable Long workplaceId) {
		return workService.deleteWorkplace(workplaceId);
	}
	
	public Work dtoToEntity(WorkDTO workDTO) {
		Work work = modelMapper.map(workDTO, Work.class);
		return work;
	}
}
