package com.cygnet.Auction.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePlayer_StatDto {

	private String playerStatId;
	private String empId;
	private String name;
	private String gender;
	private int totalRuns;
	private int totalWick;
	private int manOfTheMatch;
}
