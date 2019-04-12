package com.cygnet.Auction.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
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
import com.cygnet.Auction.responseDto.ResponsePlayerDto;
import com.cygnet.Auction.responseDto.responsePlayersForBid;
import com.cygnet.Auction.service.PlayerService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class PlayerServiceImpl implements PlayerService{
	
	private final static Logger logger = Logger.getLogger(PlayerServiceImpl.class);

	@Autowired private PlayerRepository playerRepository;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private PlayerRoleRepository playerRoleRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;

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

	public String updatePlayer(PlayerDto playerDto) {
		logger.info("With in updatePlayer");
		Player player = playerRepository.findByPlayerId(playerDto.getPlayerId());
		Employee emp = employeeRepository.findByEmpId(player.getEmployee().getEmpId());
		try {
			System.out.println("in player service impl update");
			playerDto.setUpdatedOn(uuidAndTimeStamp.getTimeStamp());
			PlayerRole playerRole = playerRoleRepository.findById(playerDto.getPlayerRole()).get();
			playerRepository.setPlayerInfoById(playerDto.getPlayerId(),playerDto.getPrefCaptain(),playerDto.getIsActive(),playerDto.getUpdatedOn(), playerRole	);
			return "Details updated successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updatePlayer for employee " + emp.getName() + " with id " + emp.getEmpId() + " with error :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in updatePlayer for employee " + emp.getName() + " with id " + emp.getEmpId() + " with error :- " + e);
			return "Please try again, due to exception :-" + e;
		}
	}


	public List<responsePlayersForBid> getPlayersForBid(int i) {
		logger.info("With in getPlayersForBid");
		try {
			return playerRepository.getPlayersForBidInAuction(i);
		}catch (Exception e){
			logger.error("Error with in getPlayersForBid :- " + e);
			return null;
		}
	}

}
