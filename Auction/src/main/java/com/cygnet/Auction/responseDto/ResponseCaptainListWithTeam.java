package com.cygnet.Auction.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCaptainListWithTeam {

	private String empId;
	private String playerId;
	private String capId;
	private String name;
	private String teamId;
	private String teamName;
}
