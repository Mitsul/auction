package com.cygnet.Auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Team;

@Component
public interface TeamRepository extends JpaRepository<Team, String> {

	Team findByCaptain(Captain captain);

	Team findByTeamId(String team_id);

}
