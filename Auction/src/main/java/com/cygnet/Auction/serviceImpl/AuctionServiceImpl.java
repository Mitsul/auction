/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the implementation class of the AuctionService class
 */

package com.cygnet.Auction.serviceImpl;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cygnet.Auction.dto.AuctionDto;
import com.cygnet.Auction.dto.ReportDto;
import com.cygnet.Auction.model.BaseToken;
import com.cygnet.Auction.model.Bidding;
import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Token;
import com.cygnet.Auction.repository.AuctionRepository;
import com.cygnet.Auction.repository.BaseTokenRepository;
import com.cygnet.Auction.repository.BiddingRepository;
import com.cygnet.Auction.repository.CaptainRepository;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.repository.PlayerRepository;
import com.cygnet.Auction.repository.TokenRepository;
import com.cygnet.Auction.responseDto.ResponseAllBidsByCaptains;
import com.cygnet.Auction.service.AuctionService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AuctionServiceImpl implements AuctionService{
	
	static Logger logger = LoggerFactory.getLogger(AuctionServiceImpl.class);

	@Autowired	private BiddingRepository biddingRepository;
	@Autowired	private PlayerRepository playerRepository;
	@Autowired	private BaseTokenRepository baseTokenRepository;
	@Autowired  private TokenRepository tokenRepository;
	@Autowired  private CaptainRepository captainRepository;
	@Autowired  private TransactionServiceImpl transactionServiceImpl;
	@Autowired  private EmployeeRepository employeeRepository; 
	@Autowired private AuctionRepository auctionRepository;

	
	/**
	 * <b> Player's Bid : </b> This function is for bidding of the player
	 * @param auctionDto This is the parameter for the playerBid function
	 * @return String This is the return of the function
	 */
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

	/**
	 * <b> getAllBidsByCaptain : </b> This function returns the every bid placed by every captain in the specified tournament
	 * @param reportDto This is the parameter for the getAllBidsByCaptain function
	 * @return List<ResponseAllBidsByCaptains> This is the return of the function
	 * @exception e This is the exception for the function
	 * @see e
	 */
	@Override
	public List<ResponseAllBidsByCaptains> getAllBidsByCaptain(ReportDto reportDto) {
		logger.info("With in getAllBidsByCaptain");
		try {
			
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(reportDto.getStartDate());
			calStart.set(Calendar.HOUR, 0);
			calStart.set(Calendar.MINUTE, 0);
			calStart.set(Calendar.SECOND, 0);
			calStart.set(Calendar.MILLISECOND, 0);
			Date modifiedStartDate = calStart.getTime();
			String start = formatter.format(modifiedStartDate);
			Date startDate = reportDto.getStartDate();

			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(reportDto.getEndDate());
			calEnd.set(Calendar.HOUR, 23);
			calEnd.set(Calendar.MINUTE, 59);
			calEnd.set(Calendar.SECOND, 59);
			calEnd.set(Calendar.MILLISECOND, 0);
			Date modifiedEndDate = calEnd.getTime();
			String end = formatter.format(modifiedEndDate);
			Date endDate = reportDto.getEndDate();
			try {
				startDate = format.parse(start);
				endDate = format.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			reportDto.setStartDate(startDate);
			reportDto.setEndDate(endDate);
			
			return auctionRepository.getAllBidsByCaptain(reportDto.getStartDate(), reportDto.getEndDate());
			
		} catch (Exception e) {
			logger.info("Exception in getAllBidsByCaptain : " + e);
			return null;
		}
	}

	/**
	 * <b> getBidsByCaptain : </b> This function returns the every bid placed by particular captain in the specified tournament
	 * @param reportDto This is the parameter for the getBidsByCaptain function
	 * @return List<ResponseAllBidsByCaptains> This is the return of the function
	 * @exception e This is the exception for the function
	 * @see e
	 */
	@Override
	public List<ResponseAllBidsByCaptains> getBidsByCaptain(ReportDto reportDto) {
		logger.info("With in getBidsByCaptain");
		try {
			
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(reportDto.getStartDate());
			calStart.set(Calendar.HOUR, 0);
			calStart.set(Calendar.MINUTE, 0);
			calStart.set(Calendar.SECOND, 0);
			calStart.set(Calendar.MILLISECOND, 0);
			Date modifiedStartDate = calStart.getTime();
			String start = formatter.format(modifiedStartDate);
			Date startDate = reportDto.getStartDate();

			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(reportDto.getEndDate());
			calEnd.set(Calendar.HOUR, 23);
			calEnd.set(Calendar.MINUTE, 59);
			calEnd.set(Calendar.SECOND, 59);
			calEnd.set(Calendar.MILLISECOND, 0);
			Date modifiedEndDate = calEnd.getTime();
			String end = formatter.format(modifiedEndDate);
			Date endDate = reportDto.getEndDate();
			try {
				startDate = format.parse(start);
				endDate = format.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			reportDto.setStartDate(startDate);
			reportDto.setEndDate(endDate);
			
			return auctionRepository.getBidsByCaptain(reportDto.getEmpId(), reportDto.getStartDate(), reportDto.getEndDate());
			
		} catch (Exception e) {
			logger.info("Exception in getBidsByCaptain : " + e);
			return null;
		}
	}
		
	
}