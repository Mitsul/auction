package com.cygnet.Auction.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.Player_StatDto;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.model.Player_Stat;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.repository.Player_StatRepository;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;
import com.cygnet.Auction.responseDto.ResponsePlayer_StatDto;
import com.cygnet.Auction.service.Player_StatService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class Player_StatServiceImpl implements Player_StatService{
	
	private final static Logger logger = Logger.getLogger(Player_StatServiceImpl.class);

	@Autowired private Player_StatRepository player_StatRepository;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;

	public String adminAddPlayerStat(Player_StatDto player_StatDto) {
		logger.info("With in adminAddPlayerStat");
		Employee emp = employeeRepository.findById(player_StatDto.getEmpId()).get();
		try {
			player_StatDto.setPlayerStatId(uuidAndTimeStamp.getUuid());
			Timestamp timestamp = uuidAndTimeStamp.getTimeStamp();
			player_StatDto.setCreationStatDatetime(timestamp);
			player_StatDto.setUpdationDatetime(timestamp);
			Player_Stat player_Stat = new Player_Stat(player_StatDto.getPlayerStatId(),emp,player_StatDto.getTotalRuns(),player_StatDto.getTotalWick(),player_StatDto.getManOfTheMatch(),player_StatDto.getCreationStatDatetime(),player_StatDto.getUpdationDatetime());
			player_StatRepository.save(player_Stat);
			return "Player's Stat. added successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in adminAddPlayerSat or the player " + emp.getName() + " with empId " + emp.getEmpId() + " error :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in adminAddPlayerSat or the player " + emp.getName() + " with empId " + emp.getEmpId() + " error :- " + e);
			return "Please try again, due to exception :-" + e;
		}
	}

	public ResponsePlayer_StatDto adminGetPlayerStat(String empId) {
		logger.info("With in adminGetPlayerStat");
		try {
			return player_StatRepository.findDetailsByEmployee(empId);
		}catch (Exception e) {
			logger.error("Error with in adminGetPlayerStat :- " + e);
			return null;
		}
	}

	public List<ResponseEmployeeDto> adminGetAllEmp() {
		logger.info("With in adminGetAllEmp");
		try {
			return new ArrayList<>(employeeRepository.findAllEmpData());
		}catch (Exception e) {
			logger.error("Error with in adminGetAllEmp :- " + e);
			return null;
		}
	}

	public String updatePlayerStat(Player_StatDto player_StatDto) {
		logger.info("With in updatePlayerStat");
		try {
			player_StatDto.setUpdationDatetime(uuidAndTimeStamp.getTimeStamp());
			player_StatRepository.setPlayerStatById(player_StatDto.getEmpId(),player_StatDto.getTotalRuns(), player_StatDto.getTotalWick(),player_StatDto.getManOfTheMatch(),player_StatDto.getUpdationDatetime());
			return "Player's Stat. updated successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updatePlayerStat :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in updatePlayerStat :- " + e);
			return "Please try again, due to exception :-\" + e";
		}
	}

	public List<ResponsePlayer_StatDto> adminPlayerStatGetAll() {
		logger.info("With in adminPlayerStatGetAll");
		try {
			return new ArrayList<>(player_StatRepository.findAllPlayerStat());
		}catch (Exception e) {
			logger.error("Error with in adminPlayerStatGetAll :- " + e);
			return null;
		}
	}
	
}
