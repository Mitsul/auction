/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the implementation class of the BaseTokenService class
 */

package com.cygnet.Auction.serviceImpl;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.Captain_ReviewDto;
import com.cygnet.Auction.model.Captain_Review;
import com.cygnet.Auction.model.Player;
import com.cygnet.Auction.repository.Captain_ReviewRepository;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.repository.PlayerRepository;
import com.cygnet.Auction.responseDto.ResponseGetCaptainReview;
import com.cygnet.Auction.responseDto.ResponsePlayersWithCapPrefDto;
import com.cygnet.Auction.service.Captain_ReviewService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class Captain_ReviewServiceImpl implements Captain_ReviewService {

	static Logger logger = LoggerFactory.getLogger(Captain_ReviewServiceImpl.class);

	@Autowired private Captain_ReviewRepository captain_ReviewRepository;
	@Autowired private PlayerRepository playerRepository;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;

	/**
	 * <b> Get Capatin's preference List : </b> This function return's the list of the players who have preferred as a captain
	 * @return List<ResponsePlayersWithCapPrefDto> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public List<ResponsePlayersWithCapPrefDto> getCaptainPrefList() {
		logger.info("With in getCaptainPrefList");
		try {
			return playerRepository.findByPreferenceCaptain(1);
		} catch (Exception e) {
			logger.info("Error with in getCaptainPrefList :- " + e);
			return null;
		}
	}

	/**
	 * <b> Give Review : </b> This function for giving the review to the players who opted as a captain
	 * @param captain_ReviewDto This is the parameter for the giveReview function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String giveReview(Captain_ReviewDto captain_ReviewDto) {
		logger.info("With in giveReview"); 
		Player player = playerRepository.findByEmployee(employeeRepository.findByEmpId(captain_ReviewDto.getEmpId()));
		Player captainPref = playerRepository.findByEmployee(employeeRepository.findByEmpId(captain_ReviewDto.getEmpCapRef()));
		System.out.println(player.getPlayerId()); 
		try {
			captain_ReviewDto.setCapRevId(uuidAndTimeStamp.getUuid());
			captain_ReviewDto.setCaptainReviewDatetime(uuidAndTimeStamp.getTimeStamp());
			Captain_Review captain_Review = new
			Captain_Review(captain_ReviewDto.getCapRevId(),captainPref,player,
			captain_ReviewDto.getRating(),captain_ReviewDto.getCaptainReviewDatetime());
			captain_ReviewRepository.save(captain_Review); 
			return "Review Submitted Successfully."; 
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in giveReiew for player " + e); 
			return "Please try again, due to exception of locking :-" + e; 
		}catch (Exception e){
			logger.error("Error with in giveReiew for player " + e); 
			return "Please try again, due to exception :-" + e; 
		}
		 
	}

	/**
	 * <b> Get Review : </b> This function is for adding the address of the employees
	 * @param empId This is the parameter for the getReview function
	 * @return ResponseGetCaptainReview This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public ResponseGetCaptainReview getReview(String empId) {
		logger.info("With in getReview");
		try {
			return captain_ReviewRepository.findByEmployee(employeeRepository.findByEmpId(empId));
		} catch (Exception e) {
			logger.error("Error with in getreview :- " + e);
			return null;
		}
	}

	/**
	 * <b> Update Review : </b> This function is for updating the review of the player
	 * @param captain_ReviewDto This is the parameter for the updateReview function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String updateReview(Captain_ReviewDto captain_ReviewDto) {
		Player player = playerRepository.findByEmployee(employeeRepository.findByEmpId(captain_ReviewDto.getEmpId()));
		Player captainPref = playerRepository.findByEmployee(employeeRepository.findByEmpId(captain_ReviewDto.getEmpCapRef()));
		logger.info("With in updateReview");
		try {
			captain_ReviewDto.setCaptainReviewDatetime(uuidAndTimeStamp.getTimeStamp());
			captain_ReviewRepository.updateCaptainReview(captain_ReviewDto.getCaptainReviewDatetime(),
					captain_ReviewDto.getRating(), player, captainPref);
			return "Review updated successfully.";
		} catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updateReview :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		} catch (Exception e) {
			logger.error("Error with in updateReview :- " + e);
			return "Please try again, due to exception :-" + e;
		}
	}
}
