package com.cygnet.Auction.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private String empId;
	private String email;
	private String name;
	private String gender;
	private String password;
	private String roles;
	@Override
	public String toString() {
		return "EmployeeDto [empId=" + empId + ", email=" + email + ", name=" + name + ", gender=" + gender
				+ ", password=" + password + ", roles=" + roles + "]";
	}
	
	
}
