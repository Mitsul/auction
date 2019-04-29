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
public class Player_StatDto {
	
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String playerStatId;
	
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String empId;
	
	
	private int totalRuns;
	private int totalWick;
	private int manOfTheMatch;
	private Date CreationStatDatetime;
	private Date UpdationDatetime;
	
	public Player_StatDto(@Size(min = 36, max = 36, message = "Something went please try again") String empId,
			int totalRuns, int totalWick, int manOfTheMatch) {
		super();
		this.empId = empId;
		this.totalRuns = totalRuns;
		this.totalWick = totalWick;
		this.manOfTheMatch = manOfTheMatch;
	}
	
}
