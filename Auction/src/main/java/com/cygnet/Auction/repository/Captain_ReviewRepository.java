/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Repository class for Captain_Review
 */

package com.cygnet.Auction.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.dto.ReturnCapFromCapReviewDto;
import com.cygnet.Auction.model.Captain_Review;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.model.Player;
import com.cygnet.Auction.responseDto.ResponseGetCaptainReview;

@Component
public interface Captain_ReviewRepository extends JpaRepository<Captain_Review, String> {

	@Modifying
	@Transactional
	@Query(value = "update Captain_Review set captainReviewDatetime = ?1, rating = ?2, cap_pref_id = ?4 where player = ?3")
	void updateCaptainReview(Date captainReviewDatetime, int rating, Player player, Player captainPref);
	
	String query_data = "select new com.cygnet.Auction.dto.ReturnCapFromCapReviewDto(c.player_cap_ref.employee.empId, c.player_cap_ref.playerId, c.player_cap_ref.employee.name,avg(c.rating)) From Captain_Review c group by c.player_cap_ref order by avg(c.rating) desc";
	@Query(value = query_data)
	List<ReturnCapFromCapReviewDto> findAllCaptain();

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseGetCaptainReview(c.player.playerId ,c.capRevId, c.player.employee.name, c.player_cap_ref.employee.name, c.rating) from Captain_Review c  where c.player.employee = ?1")
	ResponseGetCaptainReview findByEmployee(Employee emp);

	String query_data_report = "select new com.cygnet.Auction.dto.ReturnCapFromCapReviewDto(c.player_cap_ref.employee.empId, c.player_cap_ref.playerId, c.player_cap_ref.employee.name,avg(c.rating)) From Captain_Review c where c.captainReviewDatetime between ?1 and ?2 group by c.player_cap_ref order by avg(c.rating) desc";
	@Query(value = query_data_report)
	List<ReturnCapFromCapReviewDto> findAllCaptainForReport(Date startDate, Date endDate);
}
