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
public class PlayerDto {

	private String playerId;
	private String empId;
	private int prefCaptain;
	private int isActive;
	private Date joinedOn;
	private Date updatedOn;
	private String playerRole;
	
}