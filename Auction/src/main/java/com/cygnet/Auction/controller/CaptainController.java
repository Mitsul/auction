/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for Captain
 */

package com.cygnet.Auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.ReturnCapFromCapReviewDto;
import com.cygnet.Auction.responseDto.ResponseCaptainList;
import com.cygnet.Auction.responseDto.ResponsePlayersFromCaptainDto;
import com.cygnet.Auction.responseDto.ResponseString;
import com.cygnet.Auction.service.CaptainService;

@RestController
public class CaptainController {

	@Autowired CaptainService captainService;
	
	/**
	 * <b> Captains from captain review : </b> This function returns the list of the captains with the review from the captain review
	 * @return List of ReturnCapFromCapReviewDto
	 */
	@GetMapping(value = {"/admin/selectCaptains", "/employee/selectCaptains"})
	public List<ReturnCapFromCapReviewDto> selectCaptains() {
		return  captainService.selectCaptains();
	}
	
	/**
	 * <b> Select captains : </b> This function generates / selects the captains from the reviewed captain as per the size specified by the captain
	 * @param size Input type of the function setCaptains
	 * @return String
	 */
	@PostMapping(value = "/admin/setCaptains")
	public String setCaptains(@RequestParam("size") int size){
		return captainService.addCaptains(size);
	}
	
	/**
	 * <b> List of players from captain : </b> This function returns the list of the players from captain
	 * @param empId Input type of the function getPlayersFromCaptain
	 * @return List of ResponsePlayersFromCaptainDto
	 */
	@GetMapping(value = {"/admin/playersFromCaptain/{empId}", "/employee/playersFromCaptain/{empId}"})
	public List<ResponsePlayersFromCaptainDto> getPlayersFromCaptain(@PathVariable("empId") String empId){
		return captainService.getPlayersFromCaptain(empId);
		
	}
	
	/**
	 * <b> Final Captains List : </b> This function returns the final list of the captains
	 * @return List of ResponseCaptainList
	 */
	@GetMapping(value = {"/admin/getCaptains", "/employee/getCaptains"})
	public List<ResponseCaptainList> getCaptainist(){
		return captainService.getCaptainList();
	}
	
	/**
	 *  <b> Check emp as a Captain : </b> This function check whether the employee is a captain or not
	 * @param empId Input type of the function checkEmpAsCap
	 * @return String
	 */
	@GetMapping(value = {"/employee/checkEmpAsCap/{empId}","/admin/checkEmpAsCap/{empId}"})
	public String checkEmpAsCap(@PathVariable("empId") String empId) {
		return captainService.checkEmpAsCap(empId);
	}
	
	/**
	 * <b> Get employee by capId : </b> This function returns the employee from the capId
	 * @param capId Input type of the function checkEmpAsCap
	 * @return ResponseString
	 */
	@GetMapping(value = {"/employee/getEmpByCapId/{capId}","/admin/getEmpByCapId/{capId}"})
	public ResponseString getEmployeeByCapId(@PathVariable("capId") String capId) {
		return captainService.getEmployeeByCapId(capId);
	}

}
