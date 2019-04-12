 package com.cygnet.Auction.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

	private String addressId;
	@Size(min = 2, max = 20)
	private String city;
	@Size(min = 2, max = 20)
	private String state;
	//@Size(min = 10, max = 10)
	private long contactNo;
	private String empId;
	
}
