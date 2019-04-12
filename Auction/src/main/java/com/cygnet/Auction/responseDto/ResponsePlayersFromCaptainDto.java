package com.cygnet.Auction.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePlayersFromCaptainDto {

	private String capName;
	private String playerName;
	private String playerRole;
}
