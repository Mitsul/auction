/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for TeamName
 */

package com.cygnet.Auction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
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
	
	/**
	 * <b> Add Team Name : </b> This function is for adding the Team Name
	 * @param teamNameDto Input type of the function addTeamName
	 * @param err If the input type is failed as per the validation
	 * @return String
	 */
	@PostMapping(value = "/admin/teamName/add")
	public String addTeamName(@Valid @RequestBody TeamNameDto teamNameDto, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return teamNameService.addTeamName(teamNameDto);
	}
	
	/**
	 * <b> Update Team Name : </b> This function is for updating the Team Name
	 * @param teamNameDto Input type of the function addTeamName
	 * @param err If the input type is failed as per the validation
	 * @return String
	 */
	@PutMapping(value="/admin/teamName/update")
	public String updateTeamName(@Valid @RequestBody TeamNameDto teamNameDto, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return teamNameService.updateTeamName(teamNameDto);
	}
	
	/**
	 * <b> Get all Team Name : </b> This function returns the list of teamName
	 * @return List of TeamName
	 */
	@GetMapping(value = {"/admin/teamName/getAll", "/employee/teamName/getAll"})
	public List<TeamName> getAllTeamName() {
		return teamNameService.getAllTeamName();
	}
}
