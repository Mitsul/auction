/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for Timing
 */

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

	/**
	 * <b> Add Timing : </b> This function is for adding the timing
	 * @param timingDto Input type of the function addTiming
	 * @return String
	 */
	@PostMapping(value="/admin/timing")
	public String addTiming(@RequestBody @DateTimeFormat(pattern="yyyy-MM-dd") TimingDto timingDto) {
		return timingService.addTiming(timingDto);
	}
	
	/**
	 * <b> Get Timing : </b> This function is for getting the timing based on the Id
	 * @param timeId Input type of the function getTiming
	 * @return ResponseTimingDto
	 */
	@GetMapping(value= {"/admin/timing/get/{timeId}","/employee/timing/get/{timeId}"})
	public ResponseTimingDto getTiming(@PathVariable("timeId") String timeId) {
		return timingService.getTiming(timeId);
	}
	
	/**
	 * <b> Update Timing : </b> This function is for updating the timing
	 * @param timingDto Input type of the function updateTiming
	 * @return String
	 */
	@PutMapping(value="/admin/timing/update")
	public String updateTiming(@RequestBody @DateTimeFormat(pattern="yyyy-MM-dd") TimingDto timingDto) {
		return timingService.updateTiming(timingDto);
	}
	
	/**
	 * <b> Get All Timing : </b> This function is returns the list of the timing's
	 * @return List of ResponseTimingDto
	 */
	@GetMapping(value= {"/admin/timing/getall", "/employee/timing/getall"})
	public List<ResponseTimingDto> getAllTiming(){
		return timingService.getAllTiming();
	}
}
