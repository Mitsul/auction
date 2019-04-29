package com.cygnet.Auction.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.responseDto.ResponseTeamDto;
import com.cygnet.Auction.service.TeamService;

@RestController
public class TeamController {

	static Logger log = LoggerFactory.getLogger(TeamController.class);
	@Autowired TeamService teamService;
	
	@PostMapping(value = "/admin/createTeam")
	public String setTeam() {
		
		return teamService.setTeam();
	}
	
	@GetMapping(value = {"/admin/getTeam", "/employee/getTeam"})
	public List<ResponseTeamDto> getTeam(){
		log.info("in controller");
		return teamService.getTeam();
	}
}
