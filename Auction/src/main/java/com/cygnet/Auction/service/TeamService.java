package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.responseDto.ResponseTeamDto;

public interface TeamService {

	public String setTeam();
	public List<ResponseTeamDto> getTeam();
}
