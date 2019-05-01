package com.cygnet.Auction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Team;
import com.cygnet.Auction.responseDto.ResponseCaptainListWithTeam;
import com.cygnet.Auction.responseDto.ResponseTeamDto;

@Component
public interface TeamRepository extends JpaRepository<Team, String> {

	Team findByCaptain(Captain captain);

	Team findByTeamId(String team_id);

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseTeamDto(t.teamId, t.teamname.Name, t.captain.player.employee.name) from Team t")
	List<ResponseTeamDto> getAllTeamWise();

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseCaptainListWithTeam(c.player.employee.empId, c.player.playerId, c.capId ,c.player.employee.name, t.teamId, t.teamname.Name) from Captain c left join Team t on c.capId=t.captain.capId where (c.captainDatetime between ?1 and ?2)")
	List<ResponseCaptainListWithTeam> getAllCaptainsForReport(Date startDate, Date endDate);

}
