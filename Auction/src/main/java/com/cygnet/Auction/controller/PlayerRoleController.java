package com.cygnet.Auction.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.PlayerRoleDto;
import com.cygnet.Auction.model.PlayerRole;
import com.cygnet.Auction.service.PlayerRoleService;

@RestController
public class PlayerRoleController {

	@Autowired private PlayerRoleService playerRoleService;
	
	@GetMapping(value = "/admin/playerRole/add")
	public String addPlayerRole(@RequestBody PlayerRoleDto playerRoleDto) {
		return playerRoleService.addPlayerRole(playerRoleDto);
	}
	
	@DeleteMapping(value = "/admin/playerRole/delete")
	public String deletePlayerRole(@RequestBody PlayerRoleDto playerRoleDto) {
		return playerRoleService.deletePlayerRole(playerRoleDto);
	}
	
	@PutMapping(value = "/admin/playerRole/update")
	public String updatePlayerRole(@RequestBody PlayerRoleDto playerRoleDto) {
		return playerRoleService.updatePlayerRole(playerRoleDto);
	}
	
	@GetMapping(value = "/admin/playerRole/get")
	public Optional<PlayerRole> getPlayerRole(@RequestBody PlayerRoleDto playerRoleDto) {
		return playerRoleService.getPlayerRole(playerRoleDto);
	}
	
	@GetMapping(value = {"/admin/playerRole/getAll","/employee/playerRole/getAll"})
	public List<PlayerRole> getAllPlayerRole(){
		return playerRoleService.getAllPlayerRole();
	}
}
