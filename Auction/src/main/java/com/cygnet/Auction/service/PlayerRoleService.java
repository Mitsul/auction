package com.cygnet.Auction.service;

import java.util.List;
import java.util.Optional;

import com.cygnet.Auction.dto.PlayerRoleDto;
import com.cygnet.Auction.model.PlayerRole;

public interface PlayerRoleService {

	String addPlayerRole(PlayerRoleDto playerRoleDto);
	String deletePlayerRole(PlayerRoleDto playerRoleDto);
	String updatePlayerRole(PlayerRoleDto playerRoleDto);
	Optional<PlayerRole> getPlayerRole(PlayerRoleDto playerRoleDto);
	List<PlayerRole> getAllPlayerRole();

}
