package com.cygnet.Auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.ReportDto;
import com.cygnet.Auction.dto.ReturnCapFromCapReviewDto;
import com.cygnet.Auction.dto.TeamwisePlayersDto;
import com.cygnet.Auction.responseDto.ResponseAllBidsByCaptains;
import com.cygnet.Auction.responseDto.ResponseCaptainListWithTeam;
import com.cygnet.Auction.responseDto.ResponsePlayersFromCaptainDto;
import com.cygnet.Auction.service.AuctionService;
import com.cygnet.Auction.service.CaptainService;
import com.cygnet.Auction.service.TeamService;
import com.cygnet.Auction.service.Team_AllocationService;

@RestController
@RequestMapping(value = "/admin")
public class ReportController {

	@Autowired private CaptainService captainService;
	@Autowired private TeamService teamService;
	@Autowired private AuctionService auctionService;
	@Autowired private Team_AllocationService team_AllocationService;
	
	@PostMapping(value = "/playersOptedAsCaptainWithReviews")
	public List<ReturnCapFromCapReviewDto> playersOptedAsCaptainWithReviews(@RequestBody @DateTimeFormat(pattern="yyyy-MM-dd") ReportDto reportDto) {
		return captainService.selectCaptainsTimeStamp(reportDto);
	}
	
	@PostMapping(value = "/finalListOfCaptains")
	public List<ResponseCaptainListWithTeam> finalListOfCaptainsWithTeamName(@RequestBody @DateTimeFormat(pattern="yyyy-MM-dd") ReportDto reportDto) {
		return teamService.getCaptainListTimeStamp(reportDto);
	}
	
	@PostMapping(value = "/allBidsByAllCaptain")
	public List<ResponseAllBidsByCaptains> allBidsByCaptain(@RequestBody ReportDto reportDto) {
		return auctionService.getAllBidsByCaptain(reportDto);
	}
	
	@PostMapping(value = "/allBidsByCaptain")
	public List<ResponseAllBidsByCaptains> BidsByCaptain(@RequestBody ReportDto reportDto) {
		return auctionService.getBidsByCaptain(reportDto);
	}
	
	@PostMapping(value = "/listOfPlayersbyCaptain")
	public List<ResponsePlayersFromCaptainDto> listOfPlayersByCaptain(@RequestBody ReportDto reportDto) {
		return captainService.getPlayersFromCaptainTimestamp(reportDto);
	}
	
	@PostMapping(value = "/teamWisePlayers")
	public List<TeamwisePlayersDto> teamWisePlayers(@RequestBody ReportDto reportDto) {
		return team_AllocationService.findByTeamReport(reportDto);
	}
}