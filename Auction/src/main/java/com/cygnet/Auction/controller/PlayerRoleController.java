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
	
	@PostMapping(value = "/admin/playerRole/add")
	public String addPlayerRole0(@Valid @RequestBody PlayerRoleDto playerRoleDto, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return playerRoleService.addPlayerRole(playerRoleDto);
	}
	
	@PutMapping(value = "/admin/playerRole/update")
	public String updatePlayerRole(@Valid @RequestBody PlayerRoleDto playerRoleDto, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return playerRoleService.updatePlayerRole(playerRoleDto);
	}
	
	@GetMapping(value = {"/admin/playerRole/get/{id}","/employee/playerRole/get/{id}"})
	public PlayerRole getPlayerRole(@PathVariable("id") String id) {
		return playerRoleService.getPlayerRole(id);
	}
	
	@GetMapping(value = {"/admin/playerRole/getAll","/employee/playerRole/getAll"})
	public List<PlayerRole> getAllPlayerRole(){
		return playerRoleService.getAllPlayerRole();
	}
}
