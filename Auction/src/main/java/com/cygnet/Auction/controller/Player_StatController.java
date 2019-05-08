package com.cygnet.Auction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.Player_StatDto;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;
import com.cygnet.Auction.responseDto.ResponsePlayer_StatDto;
import com.cygnet.Auction.service.Player_StatService;

@RestController
public class Player_StatController {

	@Autowired Player_StatService player_StatService;

	@PostMapping(value = "/admin/playerStat/add")
	public String adminAddPlayerStat(@Valid @RequestBody Player_StatDto player_StatDto, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return player_StatService.adminAddPlayerStat(player_StatDto);
	}
	
	@GetMapping(value="/admin/playerStat/getAllEmp")
	public List<ResponseEmployeeDto> adminGetAllEmp() {
		return player_StatService.adminGetAllEmp();
	}
	
	@GetMapping(value= {"admin/player/playerStat/{empId}","/employee/player/playerStat/{empId}"})
	public ResponsePlayer_StatDto adminGetPlayerStat(@PathVariable("empId") String empId) {
		return player_StatService.adminGetPlayerStat(empId);
	}
	
	@PutMapping(value="/admin/playerStat/update")
	public String adminUpdatePlayerStat(@Valid @RequestBody Player_StatDto player_StatDto,Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		return player_StatService.updatePlayerStat(player_StatDto);
	}
	
	@GetMapping(value= {"/admin/player/playerStat/getAll","/employee/player/playerStat/getAll"})
	public List<ResponsePlayer_StatDto> adminPlayerStatGetAll() {
		return player_StatService.adminPlayerStatGetAll();
	}
	
}
