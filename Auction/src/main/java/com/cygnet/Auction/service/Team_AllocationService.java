/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for Team_Allocation
 */

package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.ReportDto;
import com.cygnet.Auction.dto.TeamwisePlayersDto;

public interface Team_AllocationService {

	public String generateTeam();
	public List<TeamwisePlayersDto> findByTeam(String teamId);
	public List<TeamwisePlayersDto> findByTeamReport(ReportDto reportDto);
}
