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
public class Player_StatDto {
	
	private String playerStatId;
	private String empId;
	private int totalRuns;
	private int totalWick;
	private int manOfTheMatch;
	private Date CreationStatDatetime;
	private Date UpdationDatetime;
	
}
