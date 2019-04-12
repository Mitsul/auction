package com.cygnet.Auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.TeamNameDto;
import com.cygnet.Auction.model.TeamName;
import com.cygnet.Auction.service.TeamNameService;

@RestController
public class TeamNameController {

	@Autowired TeamNameService teamNameService;
	
	@PostMapping(value = "/admin/teamName/add")
	public String addTeamName(@RequestBody TeamNameDto teamNameDto) {
		return teamNameService.addTeamName(teamNameDto);
	}
	
	@PutMapping(value="/admin/teamName/update")
	public String updateTeamName(@RequestBody TeamNameDto teamNameDto) {
		return teamNameService.updateTeamName(teamNameDto);
	}
	
//	@DeleteMapping(value = "/admin/teamName/delete/{teamNameId}")
//	public String deleteTeamName(@PathVariable("teamNameId") String teamNameId) {
//		return teamNameService.deleteTeamName(teamNameId);
//	}
	
	@GetMapping(value = {"/admin/teamName/getAll", "/employee/teamName/getAll"})
	public List<TeamName> getAllTeamName() {
		return teamNameService.getAllTeamName();
	}
}
