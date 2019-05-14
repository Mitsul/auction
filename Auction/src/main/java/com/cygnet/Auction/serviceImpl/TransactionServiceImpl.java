/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is transaction of the auctionServiceImpl class
 */

package com.cygnet.Auction.serviceImpl;

import javax.persistence.OptimisticLockException;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cygnet.Auction.dto.AuctionDto;
import com.cygnet.Auction.model.Auction;
import com.cygnet.Auction.model.Bidding;
import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Player;
import com.cygnet.Auction.model.Token;
import com.cygnet.Auction.repository.AuctionRepository;
import com.cygnet.Auction.repository.BiddingRepository;
import com.cygnet.Auction.repository.CaptainRepository;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.repository.PlayerRepository;
import com.cygnet.Auction.repository.TokenRepository;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class TransactionServiceImpl {
	
	static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired	private AuctionRepository auctionRepository;
	@Autowired	private BiddingRepository biddingRepository;
	@Autowired	private PlayerRepository playerRepository;
	@Autowired  private CaptainRepository captainRepository;
	@Autowired  private TokenRepository tokenRepository;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired  private UuidAndTimeStamp UuidAndTimeStamp;
	
	/**
	 * <b> Current Players Bid : </b> This function is for the bid of the current player who's bid is already placed by the other captain
	 * @param bidding This is the parameter for the currentPlayersBid function
	 * @param auctionDto This is the parameter for the currentPlayersBid function
	 * @return void This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void currentPlayersBid(Bidding bidding, AuctionDto auctionDto) {
		
		logger.info("With in currentPlayersBid");
		
		//inserting the record into auction table
		Player player = playerRepository.findByEmployee(employeeRepository.findByEmpId(auctionDto.getEmpId()));
		Captain captain = captainRepository.findByPlayer(playerRepository.findByEmployee(employeeRepository.findByEmpId(auctionDto.getCapEmpId())));
		Auction auction = new Auction(UuidAndTimeStamp.getUuid(),player,captain,auctionDto.getLastBidAmt(),UuidAndTimeStamp.getTimeStamp());
		auctionRepository.save(auction);
		// updating captain for particular player
		biddingRepository.updateData(captain,player,auctionDto.getLastBidAmt(),UuidAndTimeStamp.getTimeStamp());
		// updating tokens
		String last_captain = bidding.getCaptain().getCapId();
		String current_captain = captain.getCapId();
		if(last_captain.equals(current_captain)) {
			Token token = tokenRepository.findByCaptain(captainRepository.findByCapId(last_captain));
			float remaining_token = (token.getRemainingTokens() + bidding.getCurrBidPrice() - auctionDto.getLastBidAmt());
			tokenRepository.setRemainingToken(last_captain, remaining_token);
		}
		else {
			Token last_cap_token = tokenRepository.findByCaptain(captainRepository.findByCapId(last_captain));
			Token curr_cap_token = tokenRepository.findByCaptain(captainRepository.findByCapId(current_captain));
			float add_remaining_token = (last_cap_token.getRemainingTokens() + bidding.getCurrBidPrice());
			float remaining_tokens = (curr_cap_token.getRemainingTokens() - auctionDto.getLastBidAmt());
			tokenRepository.setRemainingToken(last_captain, add_remaining_token);
			tokenRepository.setRemainingToken(current_captain, remaining_tokens);
		}
	}

	/**
	 * <b> New Players Bid : </b> This function is for the bid of the new player who's bid is not placed before by the another captain
	 * @param auctionDto This is the parameter for the auctionDto function
	 * @return void This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void newPlayersBid(AuctionDto auctionDto) {
		logger.info("With in newPlayerBid");
		Player player = playerRepository.findByEmployee(employeeRepository.findByEmpId(auctionDto.getEmpId()));
		Captain captain = captainRepository.findByPlayer(playerRepository.findByEmployee(employeeRepository.findByEmpId(auctionDto.getCapEmpId())));
		Auction auction = new Auction(UuidAndTimeStamp.getUuid(),player,captain,auctionDto.getLastBidAmt(),UuidAndTimeStamp.getTimeStamp());
		auctionRepository.save(auction);
		
		Token token = tokenRepository.findByCaptain(captain);
		Bidding bidding_insert = new Bidding(UuidAndTimeStamp.getUuid(),captain,player,auctionDto.getLastBidAmt(),UuidAndTimeStamp.getTimeStamp());
		biddingRepository.save(bidding_insert);
		float remaining_token = token.getRemainingTokens()-auctionDto.getLastBidAmt();
		tokenRepository.setRemainingToken(captain.getCapId(),remaining_token);
	}
	
}
