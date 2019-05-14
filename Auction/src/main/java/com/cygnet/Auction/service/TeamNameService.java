/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for TeamName
 */

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
