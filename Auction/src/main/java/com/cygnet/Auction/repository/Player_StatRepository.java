/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Repository class for PlayerStat
 */

package com.cygnet.Auction.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Player_Stat;
import com.cygnet.Auction.responseDto.ResponsePlayerStatReportDto;
import com.cygnet.Auction.responseDto.ResponsePlayer_StatDto;

@Component
public interface Player_StatRepository extends JpaRepository<Player_Stat, String> {

	@Modifying
	@Transactional
	@Query(value = "update Player_Stat set totalRuns=?2, totalWick=?3, manOfTheMatch=?4, updation_datetime=?5 where employee.empId=?1")
	void setPlayerStatById(String empId, int totalRuns, int totalWick, int manOfTheMatch, Date updationDatetime);

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponsePlayer_StatDto(p.playerStatId,p.employee.empId,p.employee.name,p.employee.gender,p.totalRuns,p.totalWick,p.manOfTheMatch) from Player_Stat p where p.employee.empId = ?1")
	ResponsePlayer_StatDto findDetailsByEmployee(String empId);

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponsePlayer_StatDto(p.playerStatId,p.employee.empId,p.employee.name,p.employee.gender,p.totalRuns,p.totalWick,p.manOfTheMatch) from Player_Stat p")
	List<ResponsePlayer_StatDto> findAllPlayerStat();

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponsePlayerStatReportDto(empId, employee.name, totalRuns) from Playe_Stat order by totalRuns desc limit 10", nativeQuery = true)
	List<ResponsePlayerStatReportDto> playersWithHighestRuns();

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponsePlayerStatReportDto(empId, employee.name, manOfTheMatch) from Playe_Stat order by manOfTheMatch desc limit 10", nativeQuery = true)
	List<ResponsePlayerStatReportDto> playersWithHighestManOfTheMatch();

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponsePlayerStatReportDto(empId, employee.name, totalWick) from Playe_Stat order by totalWick desc limit 10", nativeQuery = true)
	List<ResponsePlayerStatReportDto> playersWithHighestWickets();
}
