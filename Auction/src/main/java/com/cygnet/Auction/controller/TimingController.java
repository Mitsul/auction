package com.cygnet.Auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.TimingDto;
import com.cygnet.Auction.responseDto.ResponseTimingDto;
import com.cygnet.Auction.service.TimingService;

@RestController
public class TimingController {

	@Autowired TimingService timingService;

	@PostMapping(value="/admin/timing")
	public String addTiming(@RequestBody @DateTimeFormat(pattern="yyyy-MM-dd") TimingDto timingDto) {
		return timingService.addTiming(timingDto);
	}
	
	@GetMapping(value= {"/admin/timing/get/{timeId}","/employee/timing/get/{timeId}"})
	public ResponseTimingDto getTiming(@PathVariable("timeId") String timeId) {
		return timingService.getTiming(timeId);
	}
	
	@PutMapping(value="/admin/timing/update")
	public String updateTiming(@RequestBody @DateTimeFormat(pattern="yyyy-MM-dd") TimingDto timingDto) {
		return timingService.updateTiming(timingDto);
	}
	
	@GetMapping(value= {"/admin/timing/getall", "/employee/timing/getall"})
	public List<ResponseTimingDto> getAllTiming(){
		return timingService.getAllTiming();
	}
}
