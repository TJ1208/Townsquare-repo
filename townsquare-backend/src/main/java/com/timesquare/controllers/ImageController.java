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

import com.timesquare.dto.ImageDTO;
import com.timesquare.models.Image;
import com.timesquare.services.ImageService;

@RestController
@RequestMapping("/api/image")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public List<Image> getAllImages() {
		return imageService.getAllImages();
	}
	
	@GetMapping("/{userId}")
	public List<Image> getAllUserImages(@PathVariable Long userId) {
		return imageService.getAllUserImages(userId);
	}
	
	@GetMapping("/id/{imageId}")
	public Image getImageById(@PathVariable Long imageId) throws Exception {
		return imageService.getImageById(imageId);
	}
	
	@PostMapping("/add")
	public String addImage(@RequestBody ImageDTO imageDTO) throws ParseException {
		Image image = dtoToEntity(imageDTO);
		return imageService.addImage(image);
	}
	
	@PutMapping("/update")
	public String updateImage(@RequestBody ImageDTO imageDTO) throws ParseException {
		Image image = dtoToEntity(imageDTO);
		return imageService.updateImage(image);
	}
	
	@DeleteMapping("/delete/{imageId}")
	public String deleteImage(@PathVariable Long imageId) {
		return imageService.deleteImage(imageId);
	}
	
	private Image dtoToEntity(ImageDTO imageDTO) throws ParseException {
		Image image = modelMapper.map(imageDTO, Image.class);
		return image;
	}
	
}
