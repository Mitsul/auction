package com.cygnet.Auction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.Captain_ReviewDto;
import com.cygnet.Auction.responseDto.ResponseGetCaptainReview;
import com.cygnet.Auction.responseDto.ResponsePlayersWithCapPrefDto;
import com.cygnet.Auction.service.Captain_ReviewService;

@RestController
public class Captain_ReviewController {

	@Autowired Captain_ReviewService captain_ReviewService;
	
	@GetMapping(value = {"/employee/captainReview/getAllCaptainPrefList","/admin/captainReview/getAllCaptainPrefList"})
	public List<ResponsePlayersWithCapPrefDto> getCaptainPrefList(){
		return captain_ReviewService.getCaptainPrefList();
	}
	
	@PostMapping(value = {"/employee/captainReview/giveReview","/admin/captainReview/giveReview"})
	public String giveReview(@Valid @RequestBody Captain_ReviewDto captain_ReviewDto, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return captain_ReviewService.giveReview(captain_ReviewDto);
	}
	
	@GetMapping(value = {"/employee/captainReview/getReview/{empId}","/admin/captainReview/getReview/{empId}"})
	public ResponseGetCaptainReview getReview(@PathVariable("empId")String empId) {
		return captain_ReviewService.getReview(empId);
	}
	
	@PutMapping(value = {"/employee/captainReview/updateReview","/admin/captainReview/updateReview"})
	public String updateReview(@Valid @RequestBody Captain_ReviewDto captain_ReviewDto, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return captain_ReviewService.updateReview(captain_ReviewDto);
	}
}