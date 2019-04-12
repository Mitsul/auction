package com.cygnet.Auction.service;

import java.util.List;
import java.util.Optional;

import com.cygnet.Auction.dto.TimingDto;
import com.cygnet.Auction.model.Timing;

public interface TimingService{

	public String addTiming(TimingDto timingDto);
	public Optional<Timing> getTiming(String timeId);
	public String updateTiming(TimingDto timingDto);
	public List<Timing> getAllTiming();
}
