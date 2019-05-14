/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for BaseToken
 */

package com.cygnet.Auction.service;

import com.cygnet.Auction.dto.BaseTokenDto;

public interface BaseTokenService {

	public String generateBaseTokenForAllPlayers(BaseTokenDto baseTokenDto);
}
