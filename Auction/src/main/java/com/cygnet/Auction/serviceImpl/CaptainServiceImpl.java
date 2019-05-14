/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the implementation class of the CaptainService class
 */

package com.cygnet.Auction.serviceImpl;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.OptimisticLockException;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.ReportDto;
import com.cygnet.Auction.dto.ReturnCapFromCapReviewDto;
import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Player;
import com.cygnet.Auction.repository.BiddingRepository;
import com.cygnet.Auction.repository.CaptainRepository;
import com.cygnet.Auction.repository.Captain_ReviewRepository;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.repository.PlayerRepository;
import com.cygnet.Auction.responseDto.ResponseCaptainList;
import com.cygnet.Auction.responseDto.ResponsePlayersFromCaptainDto;
import com.cygnet.Auction.responseDto.ResponseString;
import com.cygnet.Auction.service.CaptainService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class CaptainServiceImpl implements CaptainService{
	
	static Logger logger = LoggerFactory.getLogger(CaptainServiceImpl.class);

	@Autowired private CaptainRepository captainRepository;
	@Autowired private Captain_ReviewRepository captain_ReviewRepository;
	@Autowired private PlayerRepository playerRepository;
	@Autowired private BiddingRepository biddingRepository;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	
	
	/**
	 * <b> Select Captains : </b> This function return the list of the captains who opted as a captain with the average review
	 * @return List<ReturnCapFromCapReviewDto> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public List<ReturnCapFromCapReviewDto> selectCaptains() {
		logger.info("With in selectCaptains");
		try {
			return captain_ReviewRepository.findAllCaptain();
		}catch (Exception e) {
			logger.error("Error with in selectCaptains :- " + e);
			return null;
		}
	}
	
	/**
	 * <b> Add captains : </b> This function is used for generating the final list of the captains based on the review's
	 * @param size This is the parameter for the addCaptains function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String addCaptains(int size) {
		logger.info("With in addCaptains");
		try {
			List<ReturnCapFromCapReviewDto> captainList = captain_ReviewRepository.findAllCaptain();
			List<ReturnCapFromCapReviewDto> addCaptainList = new ArrayList<>();
			List<Captain> getCaptainList = new ArrayList<>();
			captainRepository.findAll().forEach(getCaptainList :: add);
			for(int i = 0 ; i < size ; i++) {
				Player player = playerRepository.findByPlayerId(captainList.get(i).getPlayerId());
				Captain captain = new Captain(uuidAndTimeStamp.getUuid(),player,uuidAndTimeStamp.getTimeStamp());
				captainRepository.save(captain);
				addCaptainList.add(captainList.get(i));
			}
			return "Captain List Generated Successfully";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in addCaptains :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Wrror with in addCaptains :- " + e); 
			return "Please try again, due to exception :- " + e;
		}
	}

	/**
	 * <b> Get players from captain : </b> This function returns the list of the players on the basis of the captain
	 * @param empId This is the parameter for the getPlayersFromCaptain function
	 * @return List<ResponsePlayersFromCaptainDto> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public List<ResponsePlayersFromCaptainDto> getPlayersFromCaptain(String empId) {
		logger.info("With in getPlayersFromCaptain");	
		try {
			return biddingRepository.getPlayersFromCaptain(empId);
		}catch (Exception e) {
			logger.error("Error with in getPlayersFromCaptain :- " + e);
			return null;
		}
	}

	/**
	 * <b> Get Captains List : </b> This function returns the final list of the captains
	 * @return List<ResponseCaptainList> This is the return of the function
	 */
	public List<ResponseCaptainList> getCaptainList() {
		return captainRepository.getAllCaptains();
	}

	/**
	 * <b> Check employee as captain : </b> This function checks whether the player is a captain or not
	 * @param empId This is the parameter for the addAddress function
	 * @return String This is the return of the function
	 */
	public String checkEmpAsCap(String empId) {
		Player player = playerRepository.findByEmployee(employeeRepository.findByEmpId(empId));
		if(player != null) {
			Captain captain =  captainRepository.findByPlayer(player);
			if(captain != null)
				return "1";
			else
				return "0";
		}
		else {
			return "0";
		}
	}

	/**
	 * <b> Select Captains time wise : </b> This function returns the list of the captains time wise
	 * @param reportDto This is the parameter for the selectCaptainsTimeStamp function
	 * @return List<ReturnCapFromCapReviewDto> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public List<ReturnCapFromCapReviewDto> selectCaptainsTimeStamp(ReportDto reportDto) {
		logger.info("With in selectCaptainsForReport");
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
			
			return captain_ReviewRepository.findAllCaptainForReport(reportDto.getStartDate(), reportDto.getEndDate());
		}catch (Exception e) {
			logger.error("Error with in selectCaptainsForReport :- " + e);
			return null;
		}
	}

	/**
	 * <b> Select players from captains time wise : </b> This function returns the list of players from captain time wise
	 * @param reportDto This is the parameter for the getPlayersFromCaptainTimestamp function
	 * @return List<ResponsePlayersFromCaptainDto> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public List<ResponsePlayersFromCaptainDto> getPlayersFromCaptainTimestamp(ReportDto reportDto) {
		logger.info("With in getPlayersFromCaptainTimestamp");
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
			
			return biddingRepository.getPlayersFromCaptainTimestamp(reportDto.getEmpId(), reportDto.getStartDate(), reportDto.getEndDate());
		}catch (Exception e) {
			logger.error("Error with in getPlayersFromCaptainTimestamp :- " + e);
			return null;
		}
	}

	/**
	 * <b> Select employee from capId : </b> This function returns employee from capId
	 * @param capId
	 * @return ResponseString
	 */
	public ResponseString getEmployeeByCapId(String capId) {
		logger.info("With in findEmployeeByCapId");
		try {
			return captainRepository.findEmployeeByCapId(capId);
		} catch (Exception e) {
			logger.error("Error with in findEmployeeByCapId :- " + e);
			return new ResponseString("Error with in findEmployeeByCapId :- " + e);
		}
	}
}