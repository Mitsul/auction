package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.PlayerRoleDto;
import com.cygnet.Auction.model.PlayerRole;

public interface PlayerRoleService {

	String addPlayerRole(PlayerRoleDto playerRoleDto);
	String deletePlayerRole(PlayerRoleDto playerRoleDto);
	String updatePlayerRole(PlayerRoleDto playerRoleDto);
	PlayerRole getPlayerRole(String id);
	List<PlayerRole> getAllPlayerRole();

}
