/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for PlayerRole
 */

package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.PlayerRoleDto;
import com.cygnet.Auction.model.PlayerRole;

public interface PlayerRoleService {

	String addPlayerRole(PlayerRoleDto playerRoleDto);
	String updatePlayerRole(PlayerRoleDto playerRoleDto);
	PlayerRole getPlayerRole(String id);
	List<PlayerRole> getAllPlayerRole();

}
