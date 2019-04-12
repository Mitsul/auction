package com.cygnet.Auction.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.model.Team;
import com.cygnet.Auction.service.TeamService;

@RestController
public class TeamController {

	private final static Logger log = Logger.getLogger(TeamController.class);
	@Autowired TeamService teamService;
	
	@PostMapping(value = "/admin/createTeam")
	public String setTeam() {
		
		return teamService.setTeam();
	}
	
	@GetMapping(value = {"/admin/getTeam", "/employee/getTeam"})
	public List<Team> getTeam(){
		log.info("in controller");
		return teamService.getTeam();
	}
}
