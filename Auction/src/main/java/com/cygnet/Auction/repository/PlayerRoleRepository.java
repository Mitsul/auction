/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Repository class for PlayerRole
 */

package com.cygnet.Auction.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.PlayerRole;

@Component
public interface PlayerRoleRepository extends JpaRepository<PlayerRole, String>{

	PlayerRole findByPlayerRoleId(String id);

	@Transactional
	@Modifying
	@Query(value = "update PlayerRole set name = ?2 where playerRoleId = ?1")
	void updatePlayerRole(String playerRoleId, String name);

}
