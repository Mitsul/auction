package com.cygnet.Auction.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class responsePlayersForBid {

	private String empId;
	private String playerId;
	private String name;
	private String lastBidderId;
	private float lastBidderAmt;
	private String playerRole;
}
