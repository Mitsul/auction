package com.cygnet.Auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.TeamNameDto;
import com.cygnet.Auction.dto.TeamwisePlayersDto;
import com.cygnet.Auction.service.Team_AllocationService;

@RestController
public class Team_AllocationController {

	@Autowired Team_AllocationService team_AllocationService;
	
	@GetMapping(value = "/admin/generateTeam")
	public String generateTeam() {
		return team_AllocationService.generateTeam();
	}
	
	@GetMapping(value = {"/admin/findByTeam", "/employee/captain/findByTeam", "/employee/findByTeam"})
	public List<TeamwisePlayersDto> findByTeam(@RequestBody TeamNameDto teamNameDto) {
		return team_AllocationService.findByTeam(teamNameDto);
	}
}
