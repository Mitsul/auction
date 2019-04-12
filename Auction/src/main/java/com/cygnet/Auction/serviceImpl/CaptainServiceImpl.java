package com.cygnet.Auction.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

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
import com.cygnet.Auction.service.CaptainService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class CaptainServiceImpl implements CaptainService{
	
	private final static Logger logger = Logger.getLogger(CaptainServiceImpl.class);

	@Autowired private CaptainRepository captainRepository;
	@Autowired private Captain_ReviewRepository captain_ReviewRepository;
	@Autowired private PlayerRepository playerRepository;
	@Autowired private BiddingRepository biddingRepository;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	
	public List<ReturnCapFromCapReviewDto> selectCaptains() {
		logger.info("With in selectCaptains");
		try {
			return captain_ReviewRepository.findAllCaptain();
		}catch (Exception e) {
			logger.error("Error with in selectCaptains :- " + e);
			return null;
		}
	}
	
	public String addCaptains(int size) {
		logger.info("Wirh in addCaptains");
		try {
			List<ReturnCapFromCapReviewDto> captainList = captain_ReviewRepository.findAllCaptain();
			List<Captain> captains = captainRepository.findAll();
			List<ReturnCapFromCapReviewDto> addCaptainList = new ArrayList<>();
			List<Captain> getCaptainList = new ArrayList<>();
			captainRepository.findAll().forEach(getCaptainList :: add);
			if(captains.size() == 0) {
				for(int i = 0 ; i < size ; i++) {
					Player player = playerRepository.findByPlayerId(captainList.get(i).getPlayerId());
					Captain captain = new Captain(uuidAndTimeStamp.getUuid(),player,uuidAndTimeStamp.getTimeStamp());
					captainRepository.save(captain);
					addCaptainList.add(captainList.get(i));
				}
				return "Captain List Generated Successfully";
			}
			else
				return "Captain List Already Generated";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in addCaptains :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Wrror with in addCaptains :- " + e); 
			return "Please try again, due to exception :-\" + e";
		}
	}

	@Override
	public List<ResponsePlayersFromCaptainDto> getPlayersFromCaptain(String empId) {
		logger.info("With in getPlayersFromCaptain");	
		try {
			return biddingRepository.getPlayersFromCaptain(empId);
		}catch (Exception e) {
			logger.error("Error with in getPlayersFromCaptain :- " + e);
			return null;
		}
	}

	public List<ResponseCaptainList> getCaptainList() {
		return captainRepository.getAllCaptains();
	}

	public String checkEmpAsCap(String empId) {
		Player player = playerRepository.findByEmployee(employeeRepository.findByEmpId(empId));
		if(player != null) {
			Captain captain =  captainRepository.findByPlayer(player);
			if(captain != null)
				return "This employee is a captain";
			else
				return "This employee is not an catain";
		}
		else {
			return "This employeee is not registered as a player";
		}
	}
}