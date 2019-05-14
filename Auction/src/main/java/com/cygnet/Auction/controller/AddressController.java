/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for Address
 */

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

	
	/**
	 * <b> Add address : </b> This function is for adding the address of the employees
	 * @param address Input type of the function addAddress
	 * @param err If the input type is failed as per the validation
	 * @return String
	 */
	@PostMapping(value = {"/employee/address","/admin/address"})
	public String addAddress(@Valid @RequestBody AddressDto address, Errors err){
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return addressService.addAddress(address);
	}
	
	/**
	 * <b> Add address : </b> This function is for updating the address of the employees
	 * @param address Input type of the function updateAddress
	 * @param err If the input type is failed as per the validation
	 * @return String
	 */
	@PutMapping(value= {"/employee/address/update","/admin/address/update"})
	public String updateAddress(@Valid @RequestBody AddressDto address, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return addressService.updateAddress(address);
	}
	
	/**
	 * <b> Get address : </b> This function is returns the address of the employees
	 * @param empId Input type of the function getAddress
	 * @return ResponseAddressDto
	 */
	@GetMapping(value= {"/employee/address/get/{empId}","/admin/address/get/{empId}"})
	public ResponseAddressDto getAddress(@PathVariable("empId") String empId) {
			return addressService.getAddress(empId);
	}
}
