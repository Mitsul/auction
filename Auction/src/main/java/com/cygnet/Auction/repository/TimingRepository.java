/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Repository class for Timing
 */

package com.cygnet.Auction.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Timing;
import com.cygnet.Auction.responseDto.ResponseTimingDto;

@Component
public interface TimingRepository extends JpaRepository<Timing, String> {

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseTimingDto(t.timeId, t.participateStartDate, t.participateEndDate, t.reviewStartDate, t.reviewEndDate, t.captainListDate, t.auctionStartDate, t.auctionEndDate) from Timing t")
	List<ResponseTimingDto> getAllTiming();

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseTimingDto(t.timeId, t.participateStartDate, t.participateEndDate, t.reviewStartDate, t.reviewEndDate, t.captainListDate, t.auctionStartDate, t.auctionEndDate) from Timing t where t.timeId =?1")
	ResponseTimingDto findByTimeId(String timeId);

	@Modifying
	@Transactional
	@Query(value = "update Timing set participateStartDate =?2, participateEndDate =?3, reviewStartDate =?4, reviewEndDate =?5, captainListDate =?6, auctionStartDate =?7, auctionEndDate =?8 where timeId =?1")
	void updateTiming(String timeId, Date participateStartDate, Date participateEndDate, Date reviewStartDate,
			Date reviewEndDate, Date captainListDate, Date auctionStartDate, Date auctionEndDate);

}
