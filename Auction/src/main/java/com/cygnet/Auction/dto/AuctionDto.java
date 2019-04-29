package com.cygnet.Auction.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto {

	@Size(min = 36, max = 36, message = "Something went please try again")
	private String auctionId;
	
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String empId;
	
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String capEmpId;
	
	private float lastBidAmt;
	private Date auctionDatetime;
}
