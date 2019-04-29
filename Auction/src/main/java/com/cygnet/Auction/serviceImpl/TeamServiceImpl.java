package com.cygnet.Auction.serviceImpl;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Team;
import com.cygnet.Auction.model.TeamName;
import com.cygnet.Auction.repository.CaptainRepository;
import com.cygnet.Auction.repository.TeamNameRepository;
import com.cygnet.Auction.repository.TeamRepository;
import com.cygnet.Auction.responseDto.ResponseTeamDto;
import com.cygnet.Auction.service.TeamService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class TeamServiceImpl implements TeamService{
	
	static Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

	@Autowired private TeamRepository teamRepository;
	@Autowired private TeamNameRepository teamNameRepository;
	@Autowired private CaptainRepository captainRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	
	public String setTeam() {
		logger.info("With in setTeam");
		try {
			List<Captain> captainList =  captainRepository.findAll();
			List<TeamName> teamNameList = teamNameRepository.findAll();
			for(int i = 0 ; i < captainList.size() ; i++) {
				Team team = new Team(uuidAndTimeStamp.getUuid(),teamNameList.get(i),captainList.get(i),uuidAndTimeStamp.getTimeStamp());
				teamRepository.save(team);
			}
			return "Team List Generated Successfully";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in setTeam :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in setTeam :- " + e);
			return "Please try again, due to exception :- " + e;
		}
	}

	public List<ResponseTeamDto> getTeam(){
		logger.info("with in getTeam");
		try {
			return teamRepository.getAllTeamWise();
		}catch (Exception e) {
			logger.error("Error with in getTeam :- " + e);
			return null;
		}
	}
}
