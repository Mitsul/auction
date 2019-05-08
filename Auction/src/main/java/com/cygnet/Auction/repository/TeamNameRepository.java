package com.cygnet.Auction.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.TeamName;

@Component
public interface TeamNameRepository extends JpaRepository<TeamName, String> {

	@Transactional
	@Modifying
	@Query(value =  "update TeamName t set t.Name = ?2 where t.teamNameId =?1")
	void updateTeamName(String teamNameId, String name);

}
