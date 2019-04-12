package com.cygnet.Auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.PlayerDto;
import com.cygnet.Auction.responseDto.ResponsePlayerDto;
import com.cygnet.Auction.service.PlayerService;

@RestController
@RequestMapping(value="/employee")
public class PlayerController {

	@Autowired PlayerService playerService;

	@PostMapping(value="/player/register")
	public String addPlayer(@RequestBody PlayerDto playerDto) {
		return playerService.addPlayer(playerDto);
	}
	
	@GetMapping(value="/player/{empId}")
	public ResponsePlayerDto getPlayer(@PathVariable("empId") String empId) {
		return playerService.getPlayer(empId);
	}
	
	@PutMapping(value="/player/update")
	public String updatePlayer(@RequestBody PlayerDto playerDto) {
		return playerService.updatePlayer(playerDto);
	}
}
