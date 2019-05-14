/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for Team_Allocation
 */

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
	
	/**
	 * <b> Generate Team : </b> This function is for generating the  team
	 * @return String
	 */
	@GetMapping(value = "/admin/generateTeam")
	public String generateTeam() {
		return team_AllocationService.generateTeam();
	}
	
	/**
	 * <b> Find by Team : </b> This function is for finding the players by team
	 * @param teamId Input type of the function findByTeam
	 * @return List of TeamwisePlayerDto
	 */
	@GetMapping(value = {"/admin/findByTeam/{teamId}", "/employee/findByTeam/{teamId}"})
	public List<TeamwisePlayersDto> findByTeam(@PathVariable("teamId") String teamId) {
		return team_AllocationService.findByTeam(teamId);
	}
}
