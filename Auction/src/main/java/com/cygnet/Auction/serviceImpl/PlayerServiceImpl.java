/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the implementation class of the PlayerService class
 */

package com.cygnet.Auction.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.PlayerDto;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.model.Player;
import com.cygnet.Auction.model.PlayerRole;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.repository.PlayerRepository;
import com.cygnet.Auction.repository.PlayerRoleRepository;
import com.cygnet.Auction.responseDto.ResponseNumericDto;
import com.cygnet.Auction.responseDto.ResponsePlayerDto;
import com.cygnet.Auction.responseDto.ResponsePlayersForBid;
import com.cygnet.Auction.service.PlayerService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class PlayerServiceImpl implements PlayerService{
	
	static Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

	@Autowired private PlayerRepository playerRepository;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private PlayerRoleRepository playerRoleRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;

	
	/**
	 * <b> Add player : </b> This function is for the registration of the employees as a player
	 * @param playerDto This is the parameter for the addPlayer function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String addPlayer(PlayerDto playerDto) {
		logger.info("With in addPlayer");
		Employee emp = employeeRepository.findById(playerDto.getEmpId()).get();
		try {
			PlayerRole playerRole = playerRoleRepository.findById(playerDto.getPlayerRole()).get();
			Timestamp timestamp = uuidAndTimeStamp.getTimeStamp();
			playerDto.setJoinedOn(timestamp);
			playerDto.setUpdatedOn(timestamp);
			playerDto.setPlayerId(uuidAndTimeStamp.getUuid());
			if (emp!= null) {
				Player player = new Player(playerDto.getPlayerId(),emp,playerDto.getPrefCaptain(),playerDto.getIsActive(),playerDto.getJoinedOn(),playerDto.getUpdatedOn(),playerRole);
				playerRepository.save(player);
				return "Registered as player successfully.";
			}
			else
				return "Something went wrong, please try again.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in addPlayer for employee  " + emp.getName() + " id " + emp.getEmpId() + " with error " + " :- " + e);
			return "Please try again, due to exception of locking :-" + e;
			
		}catch (Exception e){
			logger.error("Error with in addPlayer for employee " + emp.getName() + " id " + emp.getEmpId() + " with error " + " :- " + e);
			return "Please try again, due to exception :-" + e;
		}
	}

	/**
	 * <b> Get player : </b> This function returns the player based on the empId
	 * @param empId This is the parameter for the getPlayer function
	 * @return String This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public ResponsePlayerDto getPlayer(String empId) {
		logger.info("With in getplayer");
		try {
			Employee emp = employeeRepository.findByEmpId(empId);
			return playerRepository.findByEmployeeforPlayer(emp);
		}catch (Exception e) {
			logger.error("Error with in getPlayer for employee  :- " + e);
			return null;
		}
	}

	/**
	 * <b> Update Player : </b> This function is for updating the player
	 * @param playerDto This is the parameter for the updatePlayer function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String updatePlayer(PlayerDto playerDto) {
		try {
			System.out.println("in player service impl update");
			playerDto.setUpdatedOn(uuidAndTimeStamp.getTimeStamp());
			PlayerRole playerRole = playerRoleRepository.findById(playerDto.getPlayerRole()).get();
			playerRepository.setPlayerInfoById(playerDto.getEmpId(),playerDto.getPrefCaptain(),playerDto.getIsActive(),playerDto.getUpdatedOn(), playerRole	);
			return "Details updated successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updatePlayer for employee "  + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in updatePlayer for employee "  + e);
			return "Please try again, due to exception :-" + e;
		}
	}

	/**
	 * <b> Get Players for Bid : </b> This function returns the list of the players for bid
	 * @return List<ResponsePlayersForBid> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public List<ResponsePlayersForBid> getPlayersForBid() {
		logger.info("With in getPlayersForBid");
		try {
			return playerRepository.getPlayersForBidInAuction();
		}catch (Exception e){
			logger.error("Error with in getPlayersForBid :- " + e);
			return null;
		}
	}

	/**
	 * <b> Total count of the players : </b> This function returns the total count of the players
	 * @return ResponseNumericDto
	 */
	public ResponseNumericDto getTotalPlayersCount() {
		return new ResponseNumericDto(playerRepository.count());
	}

	/**
	 * <b> Total count of the players : </b> This function returns the total count of the active players
	 * @return ResponseNumericDto
	 */
	public ResponseNumericDto getTotalActivePlayersCount() {
		return playerRepository.getTotalActivePlayersCount();
	}
}
