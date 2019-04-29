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
import com.cygnet.Auction.service.CaptainService;

@RestController
public class CaptainController {

	@Autowired CaptainService captainService;
	
	@GetMapping(value = {"/admin/selectCaptains", "/employee/selectCaptains"})
	public List<ReturnCapFromCapReviewDto> selectCaptains() {
		return  captainService.selectCaptains();
	}
	
	@PostMapping(value = "/admin/setCaptains")
	public String setCaptains(@RequestParam("size") int size){
		return captainService.addCaptains(size);
	}
	
	@GetMapping(value = {"/admin/playersFromCaptain/{empId}", "/employee/playersFromCaptain/{empId}", "/captain/playersFromCaptain/{empId}"})
	public List<ResponsePlayersFromCaptainDto> getPlayersFromCaptain(@PathVariable("empId") String empId){
		return captainService.getPlayersFromCaptain(empId);
		
	}
	
	@GetMapping(value = {"/admin/getCaptains", "/employee/getCaptains"})
	public List<ResponseCaptainList> getCaptainist(){
		return captainService.getCaptainList();
	}
	
	@GetMapping(value = "/employee/checkEmpAsCap/{empId}")
	public String checkEmpAsCap(@PathVariable("empId") String empId) {
		return captainService.checkEmpAsCap(empId);
	}

}
