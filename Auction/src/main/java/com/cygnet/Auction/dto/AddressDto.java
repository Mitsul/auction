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

	@Size(min = 36, max = 36, message = "Something went please try again")
	private String addressId;
	
	@Size(min = 2, max = 20)
	private String city;
	
	@Size(min = 2, max = 20)
	private String state;
	
	//@Size(min = 10, max = 10)
	private long contactNo;
	
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String empId;

	public AddressDto(@Size(min = 2, max = 20) String city, @Size(min = 2, max = 20) String state, long contactNo,
			@Size(min = 36, max = 36, message = "Something went please try again") String empId) {
		super();
		this.city = city;
		this.state = state;
		this.contactNo = contactNo;
		this.empId = empId;
	}
	
	
	
	
}
