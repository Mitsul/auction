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
	
	@PostMapping(value = "admin/generateBaseTokenForAllPlayers")
	private String generateBaseTokenForAllPlayers(@RequestBody BaseTokenDto baseTokenDto){
		return baseTokenService.generateBaseTokenForAllPlayers(baseTokenDto);
	}
}
