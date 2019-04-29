package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.TeamNameDto;
import com.cygnet.Auction.dto.TeamwisePlayersDto;

public interface Team_AllocationService {

	public String generateTeam();
	public List<TeamwisePlayersDto> findByTeam(TeamNameDto teamNameDto);
}
