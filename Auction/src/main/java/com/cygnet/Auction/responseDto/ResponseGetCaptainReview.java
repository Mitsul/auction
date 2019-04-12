package com.cygnet.Auction.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGetCaptainReview {

	private String playerId;
	private String capRevId;
	private String playerName;
	private String captainName;
	private int rating;
}
