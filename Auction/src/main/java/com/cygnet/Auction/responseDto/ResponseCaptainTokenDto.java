/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the response dto for the CaptainToken
 */

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
