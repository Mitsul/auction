package com.cygnet.Auction.repository;

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

	//String query_data = "select new com.cygnet.Auction.dto.ReturnCapFromCapReviewDto(c.player_cap_ref.playerId,avg(c.rating)) From Captain_Review c group by c.player_cap_ref order by avg(c.rating) desc";
	String query_value = "select new com.cygnet.Auction.dto.TeamwisePlayersDto(t.player.employee.name) from Team_Allocation t where team_id =?1";
	@Query(value = query_value)
	List<TeamwisePlayersDto> findByTeamName(String team_id);

}
