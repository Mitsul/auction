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

	public String updateTeamName(TeamNameDto teamNameDto) {
		logger.info("With in updateTeamName");
		try {
			TeamName teamName = new TeamName(teamNameDto.getTeamNameId(),teamNameDto.getName());
			teamNameRepository.save(teamName);
			return "Team updated successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updateTeamName :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in updateTeamName :- " + e);
			return "Please try again, due to exception :-" + e;
		}
	}

//	public String deleteTeamName(String teamNameId) {
//		try {
//			teamNameRepository.deleteByTeamNameId(teamNameId);
//			return "Team deleted successfully.";
//		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
//			return "Please try again, due to exception of locking :-" + e;
//		}catch (Exception e){
//			return "Please try again, due to exception :-" + e;
//		}
//	}

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
