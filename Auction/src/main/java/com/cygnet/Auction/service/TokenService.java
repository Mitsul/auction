/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for Token
 */

package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.responseDto.ResponseCaptainTokenDto;

public interface TokenService {

	public List<ResponseCaptainTokenDto> getTokens();
	public String generateTokens(float tokens);
}
