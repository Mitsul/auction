package com.cygnet.Auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.Captain_ReviewDto;
import com.cygnet.Auction.responseDto.ResponseGetCaptainReview;
import com.cygnet.Auction.responseDto.ResponsePlayersWithCapPrefDto;
import com.cygnet.Auction.service.Captain_ReviewService;

@RestController
@RequestMapping("/employee/captainReview")
public class Captain_ReviewController {

	@Autowired Captain_ReviewService captain_ReviewService;
	
	@GetMapping(value = "/getAllCaptainPrefList")
	public List<ResponsePlayersWithCapPrefDto> getCaptainPrefList(){
		return captain_ReviewService.getCaptainPrefList();
	}
	
	@PostMapping(value = "/giveReview")
	public String giveReview(@RequestBody Captain_ReviewDto captain_ReviewDto) {
		return captain_ReviewService.giveReview(captain_ReviewDto);
	}
	
	@GetMapping(value = "/getReview/{empId}")
	public ResponseGetCaptainReview getReview(@PathVariable("empId")String empId) {
		return captain_ReviewService.getReview(empId);
	}
	
	@PutMapping(value = "/updateReview")
	public String updateReview(@RequestBody Captain_ReviewDto captain_ReviewDto) {
		return captain_ReviewService.updateReview(captain_ReviewDto);
	}
}
