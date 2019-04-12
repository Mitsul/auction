package com.cygnet.Auction.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.TeamName;

@Component
public interface TeamNameRepository extends JpaRepository<TeamName, String> {

	
	@Transactional
	@Query(value = "delete from TeamName where teamNameId =?1")
	void deleteByTeamNameId(String teamNameId);

}
