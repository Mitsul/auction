package com.cygnet.Auction.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cygnet.Auction.dto.AuctionDto;
import com.cygnet.Auction.model.BaseToken;
import com.cygnet.Auction.model.Bidding;
import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Token;
import com.cygnet.Auction.repository.BaseTokenRepository;
import com.cygnet.Auction.repository.BiddingRepository;
import com.cygnet.Auction.repository.CaptainRepository;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.repository.PlayerRepository;
import com.cygnet.Auction.repository.TokenRepository;
import com.cygnet.Auction.service.AuctionService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
//@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class AuctionServiceImpl implements AuctionService{
	
	private final static Logger logger = Logger.getLogger(AuctionServiceImpl.class);

	@Autowired	private BiddingRepository biddingRepository;
	@Autowired	private PlayerRepository playerRepository;
	@Autowired	private BaseTokenRepository baseTokenRepository;
	@Autowired  private TokenRepository tokenRepository;
	@Autowired  private CaptainRepository captainRepository;
	@Autowired  private TransactionServiceImpl transactionServiceImpl;
	@Autowired  private EmployeeRepository employeeRepository; 

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, readOnly=false, timeout = 300, rollbackFor = Exception.class)
	public String playerBid(AuctionDto auctionDto) {
		
		logger.info("With in playerBid");
		Bidding bidding = biddingRepository.findByPlayer(playerRepository.findByEmployee(employeeRepository.findByEmpId(auctionDto.getEmpId())));
		Captain captain = captainRepository.findByPlayer(playerRepository.findByEmployee(employeeRepository.findByEmpId(auctionDto.getCapEmpId())));
		Token token = tokenRepository.findByCaptain(captain);
		if(token.getRemainingTokens() >= auctionDto.getLastBidAmt()) {
			if(bidding == null) {
				BaseToken baseToken = baseTokenRepository.findByPlayer(playerRepository.findByEmployee(employeeRepository.findByEmpId(auctionDto.getEmpId())));
				if(baseToken == null)
					return "This player is not available for bidding";
				else {
					if(baseToken.getBaseToken()>auctionDto.getLastBidAmt())
						return "Sorry for inconvence but the base amount is " + baseToken.getBaseToken() + " ,so please enter amount higher than base amount";
					else {
						transactionServiceImpl.newPlayersBid(auctionDto);
						return "Bid placed successfully and inserted record in auction and bidding table";
					}
				}
			}
			else {
				if(bidding.getCurrBidPrice()>=auctionDto.getLastBidAmt())
					return "You need to place the high bid for the player " + " as the highest bid is " + bidding.getCurrBidPrice() + " by " + bidding.getCaptain().getPlayer().getEmployee().getName();
				else {
					transactionServiceImpl.currentPlayersBid(bidding,auctionDto);
					return "Bid placed successfully and updated record successfully";	
				}
			}
		}
		else {
			return "Insufficient Amount for bidding...";
		}
	}
		
	
}