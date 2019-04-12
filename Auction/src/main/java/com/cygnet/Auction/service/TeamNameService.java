package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.TeamNameDto;
import com.cygnet.Auction.model.TeamName;

public interface TeamNameService {

	public String addTeamName(TeamNameDto teamNameDto);
	public String updateTeamName(TeamNameDto teamNameDto);
//	public String deleteTeamName(String teamNameId);
	public List<TeamName> getAllTeamName();
}
