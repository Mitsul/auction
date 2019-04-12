package com.cygnet.Auction.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;

import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.PlayerRoleDto;
import com.cygnet.Auction.model.PlayerRole;
import com.cygnet.Auction.repository.PlayerRoleRepository;
import com.cygnet.Auction.service.PlayerRoleService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class PlayerRoleServiceImpl implements PlayerRoleService{
	
	private final static Logger logger = Logger.getLogger(PlayerRoleServiceImpl.class);

	@Autowired PlayerRoleRepository playerRoleRepository;
	@Autowired UuidAndTimeStamp uuidAndTimeStamp;
	
	@Override
	public String addPlayerRole(PlayerRoleDto playerRoleDto) {
		logger.info("With in addPlayerRole");
		try {
			playerRoleDto.setPlayerRoleId(uuidAndTimeStamp.getUuid());
			PlayerRole playerRole = new PlayerRole(playerRoleDto.getPlayerRoleId(),playerRoleDto.getName());
			playerRoleRepository.save(playerRole);
			return "Player Role added succcessfully";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in addPlayerRole :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in addPlayerRole :- " + e);
			return "Please try again, due to exception :-" + e;
		}
		
	}

	@Override
	public String deletePlayerRole(PlayerRoleDto playerRoleDto) {
		logger.info("With in deletePlayerRole");
		try {
			playerRoleRepository.deleteById(playerRoleDto.getPlayerRoleId());
			return "Player Role Deleted successfully";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in deletePlayerRole :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in deletePlayerRoel :- " + e); 
			return "Please try again, due to exception :-" + e;
		}
	}

	@Override
	public String updatePlayerRole(PlayerRoleDto playerRoleDto) {
		logger.info("With in updatePlayerRole");
		try {
			PlayerRole playerRole = new PlayerRole(playerRoleDto.getPlayerRoleId(), playerRoleDto.getName());
			playerRoleRepository.save(playerRole);
			return "Player Role Upedated Successfully";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updatePlayerRole :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in updatePlayerRole :- " + e);
			return "Please try again, due to exception :-" + e;
		}
		
	}

	@Override
	public Optional<PlayerRole> getPlayerRole(PlayerRoleDto playerRoleDto) {
		logger.info("With in getPlayerRole");
		try {
			return playerRoleRepository.findById(playerRoleDto.getPlayerRoleId());
		}catch (Exception e){
			logger.error("Error with in getPlayerRole :- " + e);
			return null;
		}
	}

	@Override
	public List<PlayerRole> getAllPlayerRole() {
		logger.info("With in getAllPlayerRole");
		try {
			List<PlayerRole> playerRole = new ArrayList<>();
			playerRoleRepository.findAll().forEach(playerRole :: add);
			return playerRole;
		}catch (Exception e) {
			logger.error("Error with in getAllPlayerRole :- " + e);
			return null;
		}
		
	}

}
