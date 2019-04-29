package com.cygnet.Auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
