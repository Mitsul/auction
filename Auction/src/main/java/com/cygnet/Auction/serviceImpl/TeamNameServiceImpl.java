/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the implementation class of the TeamNameService class
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

import com.cygnet.Auction.dto.TeamNameDto;
import com.cygnet.Auction.model.TeamName;
import com.cygnet.Auction.repository.TeamNameRepository;
import com.cygnet.Auction.service.TeamNameService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class TeamNameServiceImpl implements TeamNameService{
	
	static Logger logger = LoggerFactory.getLogger(TeamNameServiceImpl.class);

	@Autowired TeamNameRepository teamNameRepository;
	@Autowired UuidAndTimeStamp uuidAndTimeStamp;

	
	/**
	 * <b> Add Team Name : </b> This function is for adding the team
	 * @param teamNameDto This is the parameter for the addTeamName function
	 * @return String This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String addTeamName(TeamNameDto teamNameDto) {
		logger.info("With in addTeamName");
		try {
			teamNameDto.setTeamNameId(uuidAndTimeStamp.getUuid());
			TeamName teamName = new TeamName(teamNameDto.getTeamNameId(),teamNameDto.getName()); 
			teamNameRepository.save(teamName);
			return "Team added successfully";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in addTeamName :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error wirh in addTeamName :- " + e);
			return "Please try again, due to exception :-" + e;
		}
	}

	/**
	 * <b> Update Team Name : </b> This function is for updating the team name
	 * @param teamNameDto This is the parameter for the updateTeamName function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String updateTeamName(TeamNameDto teamNameDto) {
		logger.info("With in updateTeamName");
		try {
			teamNameRepository.updateTeamName(teamNameDto.getTeamNameId(),teamNameDto.getName());
			return "Team updated successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updateTeamName :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in updateTeamName :- " + e);
			return "Please try again, due to exception :-" + e;
		}
	}

	/**
	 * <b> Get all Team Name : </b> This function returns the list of the Team Name
	 * @return List<TeamName> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public List<TeamName> getAllTeamName() {
		logger.info("With in getAllTeamName");
		try {
			List<TeamName> teamName = new ArrayList<>();
			teamNameRepository.findAll().forEach(teamName :: add);
			return teamName;
		}catch (Exception e) {
			logger.error("Error with in getAllTeamName :- " + e);
			return null;
		}
	}
}
