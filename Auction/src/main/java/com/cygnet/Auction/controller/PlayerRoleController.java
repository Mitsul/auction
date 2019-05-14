/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for PlayerRole
 */

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

import com.cygnet.Auction.dto.PlayerRoleDto;
import com.cygnet.Auction.model.PlayerRole;
import com.cygnet.Auction.service.PlayerRoleService;

@RestController
public class PlayerRoleController {

	@Autowired private PlayerRoleService playerRoleService;
	
	/**
	 * <b> Add Player Role : </b> This function is for adding the player role
	 * @param playerRoleDto Input type of the function addPlayerRole
	 * @param err If the input type is failed as per the validation
	 * @return String
	 */
	@PostMapping(value = "/admin/playerRole/add")
	public String addPlayerRole(@Valid @RequestBody PlayerRoleDto playerRoleDto, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return playerRoleService.addPlayerRole(playerRoleDto);
	}
	
	/**
	 * <b> Update Player Role : </b> This function is for updating the player role
	 * @param playerRoleDto Input type of the function updatePlayerRole
	 * @param err If the input type is failed as per the validation
	 * @return String
	 */
	@PutMapping(value = "/admin/playerRole/update")
	public String updatePlayerRole(@Valid @RequestBody PlayerRoleDto playerRoleDto, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return playerRoleService.updatePlayerRole(playerRoleDto);
	}
	
	/**
	 * <b> Update Player Role : </b> This function returns the player role by Id
	 * @param id playerRoleDto Input type of the function getPlayerRole
	 * @return PlayerRole
	 */
	@GetMapping(value = {"/admin/playerRole/get/{id}","/employee/playerRole/get/{id}"})
	public PlayerRole getPlayerRole(@PathVariable("id") String id) {
		return playerRoleService.getPlayerRole(id);
	}
	
	/**
	 * <b> Get all Player Role : </b> This function returns list of player role
	 * @return List of PlayerRole
	 */
	@GetMapping(value = {"/admin/playerRole/getAll","/employee/playerRole/getAll"})
	public List<PlayerRole> getAllPlayerRole(){
		return playerRoleService.getAllPlayerRole();
	}
}
