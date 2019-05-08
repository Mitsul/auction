package com.cygnet.Auction.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.PlayerDto;
import com.cygnet.Auction.responseDto.ResponsePlayerDto;
import com.cygnet.Auction.service.PlayerService;

@RestController
public class PlayerController {

	@Autowired PlayerService playerService;

	@PostMapping(value= {"/employee/player/register","/admin/player/register"})
	public String addPlayer(@Valid @RequestBody PlayerDto playerDto, Errors err) {
		System.out.println("DTO: "+playerDto.toString());
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return playerService.addPlayer(playerDto);
	}
	
	@GetMapping(value= {"/employee/player/{empId}","/admin/player/{empId}"})
	public ResponsePlayerDto getPlayer(@PathVariable("empId") String empId) {
		return playerService.getPlayer(empId);
	}
	
	@PutMapping(value= {"/employee/player/update","/admin/player/update"})
	public String updatePlayer(@Valid @RequestBody PlayerDto playerDto, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return playerService.updatePlayer(playerDto);
	}
}
