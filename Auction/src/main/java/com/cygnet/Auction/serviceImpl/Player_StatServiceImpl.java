/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the implementation class of the Player_StatService class
 */

package com.cygnet.Auction.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.Player_StatDto;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.model.Player_Stat;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.repository.Player_StatRepository;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;
import com.cygnet.Auction.responseDto.ResponseNumericDto;
import com.cygnet.Auction.responseDto.ResponsePlayerStatReportDto;
import com.cygnet.Auction.responseDto.ResponsePlayer_StatDto;
import com.cygnet.Auction.service.Player_StatService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class Player_StatServiceImpl implements Player_StatService{
	
	static Logger logger = LoggerFactory.getLogger(Player_StatServiceImpl.class);

	@Autowired private Player_StatRepository player_StatRepository;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	
	/**
	 * <b> Add Player Stat : </b> This function is for adding the stat. of the players
	 * @param player_StatDto This is the parameter for the adminAddPlayerStat function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
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

	/**
	 * <b> Get address : </b> This function is for getting the player Stat of the players based on the empId
	 * @param empId This is the parameter for the adminGetPlayerStat function
	 * @return ResponsePlayer_StatDto This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public ResponsePlayer_StatDto adminGetPlayerStat(String empId) {
		logger.info("With in adminGetPlayerStat");
		try {
			return player_StatRepository.findDetailsByEmployee(empId);
		}catch (Exception e) {
			logger.error("Error with in adminGetPlayerStat :- " + e);
			return null;
		}
	}

	/**
	 * <b> Get all Employees : </b> This function returns the list of the employees
	 * @return List<ResponseEmployeeDto> This is the return of the function
	 * @exception  e This are the exceptions for the function
	 * @see e
	 */
	public List<ResponseEmployeeDto> adminGetAllEmp() {
		logger.info("With in adminGetAllEmp");
		try {
			return new ArrayList<>(employeeRepository.findAllEmpData());
		}catch (Exception e) {
			logger.error("Error with in adminGetAllEmp :- " + e);
			return null;
		}
	}

	/**
	 * <b> Update Player Stat : </b> This function is for updating the player Stat.
	 * @param player_StatDto This is the parameter for the updatePlayerStat function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
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

	/**
	 * <b> Get All Players Stat : </b> This function returns the player Stat of all the players
	 * @return List<ResponsePlayer_StatDto> This is the return of the function
	 * @exception  e This are the exceptions for the function
	 * @see e
	 */
	public List<ResponsePlayer_StatDto> adminPlayerStatGetAll() {
		logger.info("With in adminPlayerStatGetAll");
		try {
			return new ArrayList<>(player_StatRepository.findAllPlayerStat());
		}catch (Exception e) {
			logger.error("Error with in adminPlayerStatGetAll :- " + e);
			return null;
		}
	}

	/**
	 * <b> Get Players with highest runs : </b> This function returns the list of 10 players having the highest Runs
	 * @return List of ResponsePlayerStatReportDto
	 */
	public List<ResponsePlayerStatReportDto> highestRuns() {
		return player_StatRepository.playersWithHighestRuns();
	}

	/**
	 * <b> Get players with highest Man of the Match : </b> This function returns the list of 10 players having the highest ManofTheMatch
	 * @return List of ResponsePlayerStatReportDto
	 */
	public List<ResponsePlayerStatReportDto> highestManofTheMatch() {
		return player_StatRepository.playersWithHighestManOfTheMatch();
	}

	/**
	 * <b> Get players with highest wickets : </b> This function returns the list of 10 players having the highest Wickets
	 * @return List of ResponsePlayerStatReportDto
	 */
	public List<ResponsePlayerStatReportDto> highestWickets() {
		return player_StatRepository.playersWithHighestWickets();
	}

	/**
	 * <b> Total Runs : </b> This function returns the total runs
	 * @return ResponseNumericDto
	 */
	public ResponseNumericDto getTotalRuns() {
		return player_StatRepository.getTotalRuns();
	}

	/**
	 * <b> Total Wickets : </b> This function returns the total wickets
	 * @return ResponseNumericDto
	 */
	public ResponseNumericDto getTotalWickets() {
		return player_StatRepository.getTotalWIckets();
	}

	/**
	 * <b> Total Man of the Match : </b> This function returns the total man of the match
	 * @return ResponseNumericDto
	 */
	public ResponseNumericDto getTotalManOfTheMatch() {
		return player_StatRepository.getTotalManOfTheMatch();
	}
	
}
