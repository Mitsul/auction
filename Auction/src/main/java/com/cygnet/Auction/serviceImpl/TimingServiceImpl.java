package com.cygnet.Auction.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;

import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.TimingDto;
import com.cygnet.Auction.model.Timing;
import com.cygnet.Auction.repository.TimingRepository;
import com.cygnet.Auction.service.TimingService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class TimingServiceImpl implements TimingService{
	
	private final static Logger logger = Logger.getLogger(TimingServiceImpl.class);

	@Autowired private TimingRepository timingRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;

	public String addTiming(TimingDto timingDto) {
		logger.info("With in addTiming");
		try {
			timingDto.setTimeId(uuidAndTimeStamp.getUuid());
			Timing timing = new Timing(timingDto.getTimeId(),timingDto.getParticipateStartDate(),timingDto.getParticipateEndDate(),
						timingDto.getReviewStartDate(),timingDto.getReviewEndDate(),timingDto.getCaptainListDate(),
						timingDto.getAuctionStartDate(),timingDto.getAuctionEndDate());
			timingRepository.save(timing);
			return "Timings's added successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in addTiming :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in addTiming :- " + e);
			return "Please try again, due to exception :-" + e;
		}
	}

	public Optional<Timing> getTiming(String timeId) {
		logger.info("Wth in getTiming");
		try {
			return timingRepository.findById(timeId);
		}catch (Exception e) {
			logger.error("Error with in getTiming :- " + e);
			return null;
		}
	}

	public String updateTiming(TimingDto timingDto) {
		logger.info("With in updateTiming");
		try {
			Timing timing = new Timing(timingDto.getTimeId(),timingDto.getParticipateStartDate(),timingDto.getParticipateEndDate(),
					timingDto.getReviewStartDate(),timingDto.getReviewEndDate(),timingDto.getCaptainListDate(),
					timingDto.getAuctionStartDate(),timingDto.getAuctionEndDate());
			timingRepository.save(timing);
			return "Timing's updated successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updateTiming :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in updateTiming :- " + e);
			return "Please try again, due to exception :-" + e;
		}
		
	}

	public List<Timing> getAllTiming() {
		logger.info("With in getAllTiming");
		try {
			List<Timing> timing = new ArrayList<>();
			timingRepository.findAll().forEach(timing :: add);
			return timing;
		}
		catch (Exception e) {
			logger.error("Error with in getAllTiming :- " + e);
			return null;
		}
	}
}
