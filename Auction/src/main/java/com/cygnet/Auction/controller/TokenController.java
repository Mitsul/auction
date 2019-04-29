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
	
	@GetMapping(value = {"/admin/getAllTokens","/employee/getAllTokens"})
	public List<ResponseCaptainTokenDto> getTokens(){
		return tokenService.getTokens();
	}
	
	@PostMapping(value = "/admin/generateTokens")
	public String generateTokens(@RequestParam("tokens") float tokens) {
		return tokenService.generateTokens(tokens);
	}
	
}
