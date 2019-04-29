package com.cygnet.Auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.PlayerRole;

@Component
public interface PlayerRoleRepository extends JpaRepository<PlayerRole, String>{

	PlayerRole findByPlayerRoleId(String id);

}
