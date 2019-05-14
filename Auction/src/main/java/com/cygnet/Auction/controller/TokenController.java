/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for Token
 */

package com.cygnet.Auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.responseDto.ResponseCaptainTokenDto;
import com.cygnet.Auction.service.TokenService;

@RestController
public class TokenController {

	@Autowired TokenService tokenService;
	
	/**
	 * <b> List of all tokens of the captain's : </b> This function return's the list of tokens with the captain
	 * @return list of ResponseCaptainTokenDto
	 */
	@GetMapping(value = {"/admin/getAllTokens","/employee/getAllTokens"})
	public List<ResponseCaptainTokenDto> getTokens(){
		return tokenService.getTokens();
	}
	
	/**
	 * <b> Generate tokens for the captain : </b> This function generates the token for the captain
	 * @param tokens Input type of the function generateTokens
	 * @return String
	 */
	@PostMapping(value = "/admin/generateTokens")
	public String generateTokens(@RequestParam("tokens") float tokens) {
		return tokenService.generateTokens(tokens);
	}
	
}
