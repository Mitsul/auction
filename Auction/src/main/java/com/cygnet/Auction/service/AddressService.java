package com.cygnet.Auction.service;

import com.cygnet.Auction.dto.AddressDto;
import com.cygnet.Auction.responseDto.ResponseAddressDto;

public interface AddressService {
	
	public String addAddress(AddressDto address);
	public String updateAddress(AddressDto address);
	public ResponseAddressDto getAddress(String empId);
}