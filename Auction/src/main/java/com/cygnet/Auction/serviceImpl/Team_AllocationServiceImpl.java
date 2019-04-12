package com.cygnet.Auction.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.TeamNameDto;
import com.cygnet.Auction.dto.TeamwisePlayersDto;
import com.cygnet.Auction.model.Bidding;
import com.cygnet.Auction.model.Team;
import com.cygnet.Auction.model.Team_Allocation;
import com.cygnet.Auction.repository.BiddingRepository;
import com.cygnet.Auction.repository.TeamRepository;
import com.cygnet.Auction.repository.Team_AllocationRepository;
import com.cygnet.Auction.service.Team_AllocationService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class Team_AllocationServiceImpl implements Team_AllocationService{
	
	private final static Logger logger = Logger.getLogger(Team_AllocationServiceImpl.class);

	@Autowired private Team_AllocationRepository team_AllocationRepository;
	@Autowired private BiddingRepository biddingRepository;
	@Autowired private TeamRepository teamRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	 
	public String generateTeam() {
		logger.info("With in generateTeam");
		try {
			List<Bidding> bidding_list = new ArrayList<>();
			biddingRepository.findAll().forEach(bidding_list :: add);
			for(int i = 0 ; i < bidding_list.size() ; i++) {
				Bidding bidding = biddingRepository.findByBiddingId(bidding_list.get(i).getBiddingId());
				Team team = teamRepository.findByCaptain(bidding_list.get(i).getCaptain());
				Team_Allocation team_Allocation = new Team_Allocation(uuidAndTimeStamp.getUuid(),team,bidding_list.get(i).getPlayer(),bidding,uuidAndTimeStamp.getTimeStamp());
				team_AllocationRepository.save(team_Allocation);
			}
			return "Team Generated Successfully";
		}catch (Exception e) {
			logger.error("Error with in generateTeam :- " + e);
			return "Something went please try again.";
		}
	}

	public List<TeamwisePlayersDto> findByTeam(TeamNameDto teamNameDto) {
		logger.info("With in findbyTeam");
		try {
			List<TeamwisePlayersDto> team_allocation_list = new ArrayList<>();
			team_AllocationRepository.findByTeamName(teamNameDto.getTeamNameId()).forEach(team_allocation_list :: add);
			return team_allocation_list;
		}catch (Exception e) {
			logger.error("Error with in findByTeam :- " + e);
			return null;
		}
	}
}
