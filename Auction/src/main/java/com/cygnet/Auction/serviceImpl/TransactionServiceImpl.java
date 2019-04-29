package com.cygnet.Auction.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
