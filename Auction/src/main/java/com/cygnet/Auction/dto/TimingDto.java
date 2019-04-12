package com.cygnet.Auction.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimingDto {

	private String timeId;
	private Date participateStartDate;
	private Date participateEndDate;
	private Date reviewStartDate;
	private Date reviewEndDate;
	private Date captainListDate;
	private Date auctionStartDate;
	private Date auctionEndDate;
	
	public TimingDto(Date participateStartDate, Date participateEndDate, Date reviewStartDate, Date reviewEndDate,
			Date captainListDate, Date auctionStartDate, Date auctionEndDate) {
		super();
		this.participateStartDate = participateStartDate;
		this.participateEndDate = participateEndDate;
		this.reviewStartDate = reviewStartDate;
		this.reviewEndDate = reviewEndDate;
		this.captainListDate = captainListDate;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
	}
}
