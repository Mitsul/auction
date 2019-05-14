/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for Auction
 */

package com.cygnet.Auction.controller;

import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.AuctionApplication;
import com.cygnet.Auction.dto.AuctionDto;
import com.cygnet.Auction.responseDto.ResponsePlayersForBid;
import com.cygnet.Auction.service.AuctionService;
import com.cygnet.Auction.serviceImpl.PlayerServiceImpl;

@RestController
public class AuctionController {
	
	static Logger logger = LoggerFactory.getLogger(AuctionApplication.class);

	@Autowired AuctionService auctionService;
	@Autowired PlayerServiceImpl playerService;
	
	/**
	 * <b> Players for Bid : </b> This function returns the list of the players for bid
	 * @return List of ResponsePlayersForBid
	 */
	@GetMapping(value = {"/employee/getPlayers","/admin/getPlayers"})
	public List<ResponsePlayersForBid> getPlayersForBid() {
		 return playerService.getPlayersForBid(0);
	}
	
	/**
	 * <b> Add address : </b> This function is for adding the address of the employees
	 * @param auctionDto Input type of the function playerBid
	 * @param err If the input type is failed as per the validation
	 * @return String
	 */
	@PostMapping(value = {"/employee/captain/bid","/admin/captain/bid"})
	public String playerBid(@Valid @RequestBody AuctionDto auctionDto, Errors err) {
		logger.info("With in playerBid");
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else {
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
	
}
