package com.cygnet.Auction.repository;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Bidding;
import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Player;
import com.cygnet.Auction.responseDto.ResponsePlayersFromCaptainDto;

@Component
public interface BiddingRepository extends JpaRepository<Bidding, String> {
	Bidding findByPlayer(Player player);

	@Modifying
	@Transactional
	@Query(value = "update Bidding set bidding_datetime = ?4, curr_bid_price = ?3, cap_id = ?1 where player_id = ?2")
	void updateData(Captain captain, Player player, float lastBidAmt, Timestamp timestamp1);

	Bidding findByBiddingId(String biddingId);

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponsePlayersFromCaptainDto(b.captain.player.employee.name,b.player.employee.name,b.player.playerRole.name) from Bidding b where b.captain.player.employee.empId =?1")
	List<ResponsePlayersFromCaptainDto> getPlayersFromCaptain(String empId);

}
