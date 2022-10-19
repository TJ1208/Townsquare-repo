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

import com.timesquare.dto.AddressDTO;
import com.timesquare.models.Address;
import com.timesquare.services.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public List<Address> getAllAddresses() {
		return addressService.getAllAddresses();
	}
	
	@GetMapping("/{id}")
	public Address getAddressById(@PathVariable Long id) throws Exception {
		return addressService.getAddressById(id);
	}
	
	@PostMapping("/add")
	public String addAddress(@RequestBody AddressDTO addressDTO) throws ParseException {
		Address address = dtoToEntity(addressDTO);
		return addressService.addAddress(address);
	}
	
	@PutMapping("/update")
	public String updateAddress(@RequestBody AddressDTO addressDTO) throws ParseException {
		Address address = dtoToEntity(addressDTO);
		return addressService.updateAddress(address);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteAddress(@PathVariable Long id) {
		return addressService.removeAddress(id);
	}
	
	private Address dtoToEntity(AddressDTO addressDTO) throws ParseException {
		Address address = modelMapper.map(addressDTO, Address.class);
		return address;
	}
}
