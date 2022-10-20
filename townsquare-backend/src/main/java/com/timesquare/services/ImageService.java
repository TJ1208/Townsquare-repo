package com.timesquare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesquare.models.Image;
import com.timesquare.repos.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepo;
	
	public List<Image> getAllImages() {
		return imageRepo.findAll();
	}
	
	public List<Image> getAllUserImages(Long userId) {
		return imageRepo.findAll().stream()
					.filter((image) -> image.getUser().getUserId() == userId)
					.collect(Collectors.toList());
	}
	
	public Image getImageById(Long imageId) throws Exception {
		return imageRepo.findById(imageId).orElseThrow(
				() -> new Exception("Image does not exist" +
						" with id" + imageId));
	}
	
	public String addImage(Image image) {
		imageRepo.save(image);
		return "Image saved successfully";
	}
	
	public String updateImage(Image image) {
		if (imageRepo.findById(image.getImageId()).isPresent()) {
			imageRepo.save(image);
			return "Image updated successfully";
		}
		return "Image not found with id " + image.getImageId();
	}
	
	public String deleteImage(Long imageId) {
		if (imageRepo.findById(imageId).isPresent()) {
			imageRepo.deleteById(imageId);
			return "Image deleted successfully";
		}
		return "Image not found with id " + imageId;
	}
}
