/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for Report
 */

package com.cygnet.Auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.ReportDto;
import com.cygnet.Auction.dto.ReturnCapFromCapReviewDto;
import com.cygnet.Auction.dto.TeamwisePlayersDto;
import com.cygnet.Auction.responseDto.ResponseAllBidsByCaptains;
import com.cygnet.Auction.responseDto.ResponseCaptainListWithTeam;
import com.cygnet.Auction.responseDto.ResponseNumericDto;
import com.cygnet.Auction.responseDto.ResponsePlayerStatReportDto;
import com.cygnet.Auction.responseDto.ResponsePlayersFromCaptainDto;
import com.cygnet.Auction.service.AuctionService;
import com.cygnet.Auction.service.CaptainService;
import com.cygnet.Auction.service.EmployeeService;
import com.cygnet.Auction.service.PlayerService;
import com.cygnet.Auction.service.Player_StatService;
import com.cygnet.Auction.service.TeamService;
import com.cygnet.Auction.service.Team_AllocationService;

@RestController
public class ReportController {

	@Autowired private CaptainService captainService;
	@Autowired private TeamService teamService;
	@Autowired private AuctionService auctionService;
	@Autowired private Team_AllocationService team_AllocationService;
	@Autowired private Player_StatService player_StatService;
	@Autowired private EmployeeService employeeService;
	@Autowired private PlayerService playerService;
	
	/**
	 * <b> Players opted as captain with review : </b> This function returns the list of the players who opted as captain with the reviews for a particular tournament
	 * @param reportDto Input type of the function playersOptedAsCaptainWithReviews
	 * @return List of ReturnCapFromCapReviewDto
	 */
	@PostMapping(value = "/admin/playersOptedAsCaptainWithReviews")
	public List<ReturnCapFromCapReviewDto> playersOptedAsCaptainWithReviews(@RequestBody @DateTimeFormat(pattern="yyyy-MM-dd") ReportDto reportDto) {
		return captainService.selectCaptainsTimeStamp(reportDto);
	}
	
	/**
	 * <b> Captain's with Team Name : </b> This function returns the list of the captains with the team name for the particular tournament
	 * @param reportDto Input type of the function finalListOfCaptainsWithTeamName
	 * @return List of ResponseCaptainListWithTeam
	 */
	@PostMapping(value = "/admin/finalListOfCaptains")
	public List<ResponseCaptainListWithTeam> finalListOfCaptainsWithTeamName(@RequestBody @DateTimeFormat(pattern="yyyy-MM-dd") ReportDto reportDto) {
		return teamService.getCaptainListTimeStamp(reportDto);
	}
	
	/**
	 * <b> All bid's by Captain : </b> This function returns the every bid placed by the captain for particular tournament
	 * @param reportDto Input type of the function allBidsByCaptain
	 * @return List of ResponseAllBidsByCaptains
	 */
	@PostMapping(value = "/admin/allBidsByAllCaptain")
	public List<ResponseAllBidsByCaptains> allBidsByCaptain(@RequestBody ReportDto reportDto) {
		return auctionService.getAllBidsByCaptain(reportDto);
	}
	
	/**
	 * <b> Final bid's Captain : </b> This function returns the final bid's placed by the captain for particular tournament
	 * @param reportDto Input type of the function allBidsByCaptain
	 * @return List of ResponseAllBidsByCaptains
	 */
	@PostMapping(value = "/admin/allBidsByCaptain")
	public List<ResponseAllBidsByCaptains> BidsByCaptain(@RequestBody ReportDto reportDto) {
		return auctionService.getBidsByCaptain(reportDto);
	}
	
	/**
	 * <b> List of players by captain : </b> This function returns the list of players by captain for particular tournament
	 * @param reportDto Input type of the function allBidsByCaptain
	 * @return List of ResponsePlayersFromCaptainDto
	 */
	@PostMapping(value = "/admin/listOfPlayersbyCaptain")
	public List<ResponsePlayersFromCaptainDto> listOfPlayersByCaptain(@RequestBody ReportDto reportDto) {
		return captainService.getPlayersFromCaptainTimestamp(reportDto);
	}
	
	/**
	 * <b> List of players team wise : </b> This function returns the list of players team wise for particular tournament
	 * @param reportDto Input type of the function teamWisePlayers
	 * @return List of TeamwisePlayersDto
	 */
	@PostMapping(value = "/admin/teamWisePlayers")
	public List<TeamwisePlayersDto> teamWisePlayers(@RequestBody ReportDto reportDto) {
		return team_AllocationService.findByTeamReport(reportDto);
	}
	
	/**
	 * <b> Highest Runs : </b> This function returns the list of 10 players having the highest runs
	 * @return List of ResponsePlayerStatReportDto
	 */
	@GetMapping(value = {"/admin/highestRun","/employee/highestRun"})
	public List<ResponsePlayerStatReportDto> highestRuns(){
		return player_StatService.highestRuns();
	}
	
	/**
	 * <b> Highest Wickets : </b> This function returns the list of 10 players having the highest Wickets
	 * @return List of ResponsePlayerStatReportDto
	 */
	@GetMapping(value = {"/admin/highestWick","/employee/highestWick"})
	public List<ResponsePlayerStatReportDto> highestWickets(){
		return player_StatService.highestWickets();
	}
	
	/**
	 * <b> Highest Man of The Match : </b> This function returns the list of 10 players having the highest Man of The Match
	 * @return List of ResponsePlayerStatReportDto
	 */
	@GetMapping(value = {"/admin/highestManOfTheMatch","/employee/highestManOfTheMatch"})
	public List<ResponsePlayerStatReportDto> highestManofTheMatch(){
		return player_StatService.highestManofTheMatch();
	}
	
	/**
	 * <b> Total count of the employees : </b> This function returns the total count of the employees
	 * @return ResponseNumericDto
	 */
	@GetMapping(value = {"/admin/getTotalEmployeesCount","/employee/getTotalEmployeesCount"})
	public ResponseNumericDto getTotalEmployeesCount() {
		return employeeService.getTotalEmployeesCount();
	}
	
	/**
	 * <b> Total count of the players : </b> This function returns the total count of the players
	 * @return ResponseNumericDto
	 */
	@GetMapping(value = {"/admin/getTotalPlayersCount","/employee/getTotalCount"})
	public ResponseNumericDto getTotalPlayersCount() {
		return playerService.getTotalPlayersCount();
	}
	
	/**
	 * <b> Total count of the players : </b> This function returns the total count of the active players
	 * @return ResponseNumericDto
	 */
	@GetMapping(value = {"/admin/getTotalActivePlayersCount","/employee/getTotalActivePlayersCount"})
	public ResponseNumericDto getTotalActivePlayersCount() {
		return playerService.getTotalActivePlayersCount();
	}
	
	/**
	 * <b> Total Runs : </b> This function returns the total runs
	 * @return ResponseNumericDto
	 */
	@GetMapping(value = {"/admin/getTotalRuns","/employee/getTotalRuns"})
	public ResponseNumericDto getTotalRuns() {
		return player_StatService.getTotalRuns();
	}
	
	/**
	 * <b> Total Wickets : </b> This function returns the total wickets
	 * @return ResponseNumericDto
	 */
	@GetMapping(value = {"/admin/getTotalWickets","/employee/getTotalWickets"})
	public ResponseNumericDto getTotalWickets() {
		return player_StatService.getTotalWickets();
	}
	
	/**
	 * <b> Total Man of the Match : </b> This function returns the total man of the match
	 * @return ResponseNumericDto
	 */
	@GetMapping(value = {"/admin/getTotalManOfTheMatch","/employee/getTotalManOfTheMatch"})
	public ResponseNumericDto getTotalManOfTheMatch() {
		return player_StatService.getTotalManOfTheMatch();
	}
}