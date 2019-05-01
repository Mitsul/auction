package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.ReportDto;
import com.cygnet.Auction.responseDto.ResponseCaptainListWithTeam;
import com.cygnet.Auction.responseDto.ResponseTeamDto;

public interface TeamService {

	public String setTeam();
	public List<ResponseTeamDto> getTeam();
	public List<ResponseCaptainListWithTeam> getCaptainListTimeStamp(ReportDto reportDto);
}
