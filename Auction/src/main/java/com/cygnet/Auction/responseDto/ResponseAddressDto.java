package com.cygnet.Auction.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAddressDto {

	private String addressId;
	private String empId;
	private String name;
	private String city;
	private String state;
	private long contactNo;
}