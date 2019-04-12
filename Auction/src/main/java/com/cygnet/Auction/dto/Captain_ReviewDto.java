package com.cygnet.Auction.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Captain_ReviewDto {

	private String capRevId;
	private String empId;
	private String empCapRef;
	private int rating;
	private Date captainReviewDatetime;

}
