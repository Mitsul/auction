/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Repository class for Auction
 */

package com.cygnet.Auction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Auction;
import com.cygnet.Auction.responseDto.ResponseAllBidsByCaptains;

@Component
public interface AuctionRepository extends JpaRepository<Auction, String> {

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseAllBidsByCaptains(a.auctionId, a.player.employee.empId, a.player.playerId, a.player.employee.name, a.captain.player.employee.empId, a.captain.player.playerId, a.captain.capId, a.captain.player.employee.name, a.lastBidAmt) from Auction a where (a.auctionDatetime between ?1 and ?2)")
	List<ResponseAllBidsByCaptains> getAllBidsByCaptain(Date startDate, Date endDate);

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseAllBidsByCaptains(a.auctionId, a.player.employee.empId, a.player.playerId, a.player.employee.name, a.captain.player.employee.empId, a.captain.player.playerId, a.captain.capId, a.captain.player.employee.name, a.lastBidAmt) from Auction a where a.captain.player.employee.empId =?1 and (a.auctionDatetime between ?2 and ?3)")
	List<ResponseAllBidsByCaptains> getBidsByCaptain(String empId, Date startDate, Date endDate);
}
