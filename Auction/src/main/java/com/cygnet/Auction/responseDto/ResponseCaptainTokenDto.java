package com.cygnet.Auction.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCaptainTokenDto {

	private String empId;
	private String playerId;
	private String capId;
	private String tokenId;
	private String capName;
	private float tokens;
	private float remainingTokens;
}
