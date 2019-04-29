package com.cygnet.Auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Player;
import com.cygnet.Auction.responseDto.ResponseCaptainList;

@Component
public interface CaptainRepository extends JpaRepository<Captain, String> {

	Captain findByCapId(String capId);

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseCaptainList(c.player.employee.empId, c.player.playerId, c.capId ,c.player.employee.name) from Captain c")
	List<ResponseCaptainList> getAllCaptains();

	Captain findByPlayer(Player player);

}
