/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for Team
 */

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
	
	/**
	 * <b> Set team : </b> This function sets the team for the captains
	 * @return String
	 */
	@PostMapping(value = "/admin/createTeam")
	public String setTeam() {
		
		return teamService.setTeam();
	}
	
	/**
	 * <b> Get Teams : </b> This function returns the list of the team's
	 * @return List of ResponseTeamDto
	 */
	@GetMapping(value = {"/admin/getTeam", "/employee/getTeam"})
	public List<ResponseTeamDto> getTeam(){
		log.info("Within getTeam controller");
		return teamService.getTeam();
	}
}
