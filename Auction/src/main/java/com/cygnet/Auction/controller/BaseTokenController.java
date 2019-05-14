/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for BaseToken
 */

package com.cygnet.Auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.BaseTokenDto;
import com.cygnet.Auction.service.BaseTokenService;

@RestController
public class BaseTokenController {

	@Autowired private BaseTokenService baseTokenService;
	
	/**
	 * <b> Generate Base Token : </b> This function generated the base token for all the players
	 * @param baseTokenDto Input type of the function addPlayerRole
	 * @return String
	 */
	@PostMapping(value = "/admin/generateBaseTokenForAllPlayers")
	public String generateBaseTokenForAllPlayers(@RequestBody BaseTokenDto baseTokenDto){
		return baseTokenService.generateBaseTokenForAllPlayers(baseTokenDto);
	}
}
