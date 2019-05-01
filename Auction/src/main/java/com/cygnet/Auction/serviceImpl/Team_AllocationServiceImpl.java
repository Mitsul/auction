package com.cygnet.Auction.serviceImpl;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.ReportDto;
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
	
	static Logger logger = LoggerFactory.getLogger(Team_AllocationServiceImpl.class);

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

	public List<TeamwisePlayersDto> findByTeam(String teamId) {
		logger.info("With in findbyTeam");
		try {
			List<TeamwisePlayersDto> team_allocation_list = new ArrayList<>();
			team_AllocationRepository.findByTeamName(teamId).forEach(team_allocation_list :: add);
			return team_allocation_list;
		}catch (Exception e) {
			logger.error("Error with in findByTeam :- " + e);
			return null;
		}
	}

	@Override
	public List<TeamwisePlayersDto> findByTeamReport(ReportDto reportDto) {
		logger.info("With in findByTeamReport");
		try {
			
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(reportDto.getStartDate());
			calStart.set(Calendar.HOUR, 0);
			calStart.set(Calendar.MINUTE, 0);
			calStart.set(Calendar.SECOND, 0);
			calStart.set(Calendar.MILLISECOND, 0);
			Date modifiedStartDate = calStart.getTime();
			String start = formatter.format(modifiedStartDate);
			Date startDate = reportDto.getStartDate();

			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(reportDto.getEndDate());
			calEnd.set(Calendar.HOUR, 23);
			calEnd.set(Calendar.MINUTE, 59);
			calEnd.set(Calendar.SECOND, 59);
			calEnd.set(Calendar.MILLISECOND, 0);
			Date modifiedEndDate = calEnd.getTime();
			String end = formatter.format(modifiedEndDate);
			Date endDate = reportDto.getEndDate();
			try {
				startDate = format.parse(start);
				endDate = format.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			reportDto.setStartDate(startDate);
			reportDto.setEndDate(endDate);
			
			return team_AllocationRepository.getPlayersByTeamReport(reportDto.getId(), reportDto.getStartDate(), reportDto.getEndDate());
		}catch (Exception e) {
			logger.error("Error with in findByTeamReport :- " + e);
			return null;
		}
	}
}
