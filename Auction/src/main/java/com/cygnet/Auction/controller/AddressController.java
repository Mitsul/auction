package com.cygnet.Auction.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.AddressDto;
import com.cygnet.Auction.responseDto.ResponseAddressDto;
import com.cygnet.Auction.service.AddressService;

@RestController
@RequestMapping(value = "/employee")
public class AddressController {

	@Autowired AddressService addressService;

	@PostMapping(value = "/address")
	public String addAddress(@Valid @RequestBody AddressDto address, Errors err){
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return addressService.addAddress(address);
	}
	
	@PutMapping(value="/address/update")
	public String updateAddress(@Valid @RequestBody AddressDto address, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return addressService.updateAddress(address);
	}
	
	@GetMapping(value="/address/get/{empId}")
	public ResponseAddressDto getAddress(@PathVariable("empId") String empId) {
			return addressService.getAddress(empId);
	}
}
