package com.cygnet.Auction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseTokenDto {

	private String baseTokenId;
	private String playerId;
	private float baseToken;
}
