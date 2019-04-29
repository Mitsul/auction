package com.cygnet.Auction.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmployeeDto {
	
	private String empId;
	private String email;
	private String name;
	private String gender;
	private String roles;
}
