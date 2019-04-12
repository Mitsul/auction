package com.cygnet.Auction.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.TimingDto;
import com.cygnet.Auction.model.Timing;
import com.cygnet.Auction.service.TimingService;

@RestController
public class TimingController {

	@Autowired TimingService timingService;

	@PostMapping(value="/admin/timing")
	public String addTiming(@RequestBody @DateTimeFormat(pattern="yyyy-MM-dd") TimingDto timingDto) {
		return timingService.addTiming(timingDto);
	}
	
	@PostMapping(value="/admin/timing/get")
	public Optional<Timing> getTiming(@RequestParam String timeId) {
		return timingService.getTiming(timeId);
	}
	
	@PutMapping(value="/admin/timing/update")
	public String updateTiming(@RequestBody @DateTimeFormat(pattern="yyyy-MM-dd") TimingDto timingDto) {
		return timingService.updateTiming(timingDto);
	}
	
	@PostMapping(value= {"/admin/timing/getall", "/employee/timing/getall"})
	public List<Timing> getAllTiming(){
		return timingService.getAllTiming();
	}
}
