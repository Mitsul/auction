/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Dto class for Employee
 */

package com.cygnet.Auction.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	@Size(min = 36, max = 36, message = "Something went please try again")
	private String empId;
	
	@Email
	private String email;
	
	@Size(min = 3, max = 25, message = "The length of the name should be between 3 to 25")
	private String name;
	
	@Size(min = 4, max = 6, message = "Something went wrong with the gender, please try again")
	private String gender;
	
	private String password;
	
	@Size(min = 10, max = 13, message = "Something went with the role, please try again")
	private String roles;
	
}
