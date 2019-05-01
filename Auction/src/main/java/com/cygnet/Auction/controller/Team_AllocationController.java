package com.cygnet.Auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.TeamwisePlayersDto;
import com.cygnet.Auction.service.Team_AllocationService;

@RestController
public class Team_AllocationController {

	@Autowired Team_AllocationService team_AllocationService;
	
	@GetMapping(value = "/admin/generateTeam")
	public String generateTeam() {
		return team_AllocationService.generateTeam();
	}
	
	@GetMapping(value = {"/admin/findByTeam/{teamId}", "/employee/captain/findByTeam/{teamId}", "/employee/findByTeam/{teamId}"})
	public List<TeamwisePlayersDto> findByTeam(@PathVariable("teamId") String teamId) {
		return team_AllocationService.findByTeam(teamId);
	}
}
