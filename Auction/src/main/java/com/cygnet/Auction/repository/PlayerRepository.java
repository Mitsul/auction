package com.cygnet.Auction.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.model.Player;
import com.cygnet.Auction.model.PlayerRole;
import com.cygnet.Auction.responseDto.ResponsePlayerDto;
import com.cygnet.Auction.responseDto.ResponsePlayersWithCapPrefDto;
import com.cygnet.Auction.responseDto.ResponsePlayersForBid;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {


	@Query(value = "select new com.cygnet.Auction.responseDto.ResponsePlayerDto(p.playerId, p.employee.empId, p.employee.email, p.employee.name, p.employee.gender, p.isActive, p.prefCaptain, p.playerRole.name) from Player p where p.employee = ?1")
	ResponsePlayerDto findByEmployeeforPlayer(Employee emp);

	@Modifying
	@Transactional
	@Query(value = "update Player set prefCaptain = ?2, isActive = ?3, updatedOn = ?4, playerRole = ?5 where employee.empId = ?1")
	void setPlayerInfoById(String empId, int prefCaptain, int isActive, Date updatedOn, PlayerRole playerRole);

	@Modifying
	@Transactional
	@Query(value = "select new com.cygnet.Auction.responseDto.ResponsePlayersWithCapPrefDto(p.playerId, p.employee.empId, p.employee.name) from Player p where preference_captain = ?1")
	List<ResponsePlayersWithCapPrefDto> findByPreferenceCaptain(int i); // for captain review

	Player findByPlayerId(String playerId); // for captain_reviewService

	List<Player> getPlayersForBid(int flag); // used for named query
	List<ResponsePlayersForBid> getPlayersForBidInAuction(int flag); // used for named query

	Player findByEmployee(Employee emp);
	
}
