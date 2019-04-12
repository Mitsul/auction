package com.cygnet.Auction.controller;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.AuctionDto;
import com.cygnet.Auction.responseDto.responsePlayersForBid;
import com.cygnet.Auction.service.AuctionService;
import com.cygnet.Auction.serviceImpl.PlayerServiceImpl;

@RestController
public class AuctionController {
	
	private final static Logger logger = Logger.getLogger(AuctionController.class);

	@Autowired AuctionService auctionService;
	@Autowired PlayerServiceImpl playerService;
	
	@GetMapping(value = "/employee/captain/getPlayers")
	public List<responsePlayersForBid> getPlayersForBid() {
		 return playerService.getPlayersForBid(0);
	}
	
	@PostMapping(value = "/employee/captain/bid")
	public String playerBid(@RequestBody AuctionDto auctionDto) {
		logger.info("With in playerBid");
		try {
			return auctionService.playerBid(auctionDto);
		}catch (LockAcquisitionException | OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			return auctionService.playerBid(auctionDto);
		}catch (Exception e){
			logger.error("Error with in playerBid :- " + e);
			return "Please try again, due to exception :-" + e;
		}
	}
	
}
