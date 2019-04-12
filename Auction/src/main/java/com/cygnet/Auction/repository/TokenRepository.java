package com.cygnet.Auction.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Token;
import com.cygnet.Auction.responseDto.ResponseCaptainTokenDto;

@Component
public interface TokenRepository extends JpaRepository<Token, String> {
	Token findByCaptain(String lastBidderId);

	@Modifying
	@Transactional
	@Query(value="update Token set remaining_tokens = ?2 where cap_id = ?1")
	void setRemainingToken(String lastBidderId, float token);
	
	Token findByCaptain(Captain findById);

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseCaptainTokenDto(t.captain.player.employee.empId, t.captain.player.playerId, t.captain.capId,t.tokenId , t.captain.player.employee.name, t.tokens, t.remainingTokens) from Token t")
	List<ResponseCaptainTokenDto> captainToken();


}
