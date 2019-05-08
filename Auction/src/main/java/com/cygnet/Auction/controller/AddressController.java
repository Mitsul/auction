package com.cygnet.Auction.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.AddressDto;
import com.cygnet.Auction.responseDto.ResponseAddressDto;
import com.cygnet.Auction.service.AddressService;

@RestController
public class AddressController {

	@Autowired AddressService addressService;

	@PostMapping(value = {"/employee/address","/admin/address"})
	public String addAddress(@Valid @RequestBody AddressDto address, Errors err){
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return addressService.addAddress(address);
	}
	
	@PutMapping(value= {"/employee/address/update","/admin/address/update"})
	public String updateAddress(@Valid @RequestBody AddressDto address, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return addressService.updateAddress(address);
	}
	
	@GetMapping(value= {"/employee/address/get/{empId}","/admin/address/get/{empId}"})
	public ResponseAddressDto getAddress(@PathVariable("empId") String empId) {
			return addressService.getAddress(empId);
	}
}
