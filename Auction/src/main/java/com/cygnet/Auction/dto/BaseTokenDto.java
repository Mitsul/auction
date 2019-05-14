/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Dto class for BaseToken
 */

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
	
	public BaseTokenDto(float baseToken) {
		super();
		this.baseToken = baseToken;
	}
}
