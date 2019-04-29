package com.cygnet.Auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cygnet.Auction.model.BaseToken;
import com.cygnet.Auction.model.Player;

public interface BaseTokenRepository extends JpaRepository<BaseToken, String>{

	BaseToken findByPlayer(Player findByPlayerId);

}
