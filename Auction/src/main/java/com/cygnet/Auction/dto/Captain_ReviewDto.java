/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Dto class for Captain_Review
 */

package com.cygnet.Auction.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Captain_ReviewDto {

	@Size(min = 36, max = 36, message = "Something went please try again")
	private String capRevId;
	
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String empId;
	
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String empCapRef;
	
	private int rating;
	private Date captainReviewDatetime;

}
