/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for Timing
 */

package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.TimingDto;
import com.cygnet.Auction.responseDto.ResponseTimingDto;

public interface TimingService{

	public String addTiming(TimingDto timingDto);
	public ResponseTimingDto getTiming(String timeId);
	public String updateTiming(TimingDto timingDto);
	public List<ResponseTimingDto> getAllTiming();
}
