/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the implementation class of the PlayerRoleService class
 */

package com.cygnet.Auction.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	static Logger logger = LoggerFactory.getLogger(PlayerRoleServiceImpl.class);

	@Autowired PlayerRoleRepository playerRoleRepository;
	@Autowired UuidAndTimeStamp uuidAndTimeStamp;
	
	/**
	 * <b> Add PlayerRole : </b> This function is for adding the Player Role
	 * @param playerRoleDto This is the parameter for the addPlayerRole function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
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
	
	/**
	 * <b> Update player Role : </b> This function is for updating the player role
	 * @param playerRoleDto This is the parameter for the updatePlayerRole function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	@Override
	public String updatePlayerRole(PlayerRoleDto playerRoleDto) {
		logger.info("With in updatePlayerRole");
		try {
			playerRoleRepository.updatePlayerRole(playerRoleDto.getPlayerRoleId(), playerRoleDto.getName());
			return "Player Role Upedated Successfully";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updatePlayerRole :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in updatePlayerRole :- " + e);
			return "Please try again, due to exception :-" + e;
		}
		
	}

	/**
	 * <b> Get Player Role : </b> This function is returns the player role based on the player role id
	 * @param id This is the parameter for the getPlayerRole function
	 * @return PlayerRole This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	@Override
	public PlayerRole getPlayerRole(String id) {
		logger.info("With in getPlayerRole");
		try {
			return playerRoleRepository.findByPlayerRoleId(id);
		}catch (Exception e){
			logger.error("Error with in getPlayerRole :- " + e);
			return null;
		}
	}

	/**
	 * <b> Get all Player Roles : </b> This function returns the list of the player roles
	 * @return List<PlayerRole> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
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
