/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Repository class for Team_Allocation
 */

package com.cygnet.Auction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.dto.TeamwisePlayersDto;
import com.cygnet.Auction.model.Team;
import com.cygnet.Auction.model.Team_Allocation;

@Component
public interface Team_AllocationRepository extends JpaRepository<Team_Allocation, String> {

	List<Team_Allocation> findAllByTeam(Team team);

	String query_value = "select new com.cygnet.Auction.dto.TeamwisePlayersDto(t.player.employee.name, t.player.playerRole.name, t.team.teamname.Name, t.team.captain.player.employee.name) from Team_Allocation t where t.team.teamId =?1";
	@Query(value = query_value)
	List<TeamwisePlayersDto> findByTeamName(String team_id);
	
	String query = "select new com.cygnet.Auction.dto.TeamwisePlayersDto(t.player.employee.name, t.player.playerRole.name, t.team.teamname.Name, t.team.captain.player.employee.name) from Team_Allocation t where t.team.teamId =?1 and teamAllocationDatetime between ?2 and ?3";
	@Query(value = query)
	List<TeamwisePlayersDto> getPlayersByTeamReport(String id, Date startDate, Date endDate);

}
