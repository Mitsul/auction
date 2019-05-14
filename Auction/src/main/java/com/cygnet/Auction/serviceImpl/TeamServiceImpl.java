/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the implementation class of the TeamService class
 */

package com.cygnet.Auction.serviceImpl;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.OptimisticLockException;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.ReportDto;
import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Team;
import com.cygnet.Auction.model.TeamName;
import com.cygnet.Auction.repository.CaptainRepository;
import com.cygnet.Auction.repository.TeamNameRepository;
import com.cygnet.Auction.repository.TeamRepository;
import com.cygnet.Auction.responseDto.ResponseCaptainListWithTeam;
import com.cygnet.Auction.responseDto.ResponseTeamDto;
import com.cygnet.Auction.service.TeamService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class TeamServiceImpl implements TeamService{
	
	static Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

	@Autowired private TeamRepository teamRepository;
	@Autowired private TeamNameRepository teamNameRepository;
	@Autowired private CaptainRepository captainRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	
	/**
	 * <b> Set Team : </b> This function is for allocating the team to the captains
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String setTeam() {
		logger.info("With in setTeam");
		try {
			List<Captain> captainList =  captainRepository.findAll();
			List<TeamName> teamNameList = teamNameRepository.findAll();
			for(int i = 0 ; i < captainList.size() ; i++) {
				Team team = new Team(uuidAndTimeStamp.getUuid(),teamNameList.get(i),captainList.get(i),uuidAndTimeStamp.getTimeStamp());
				teamRepository.save(team);
			}
			return "Team List Generated Successfully";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in setTeam :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in setTeam :- " + e);
			return "Please try again, due to exception :- " + e;
		}
	}

	/**
	 * <b> Get team : </b> This function is returns the list of the players team wise
	 * @return List<ResponseTeamDto> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public List<ResponseTeamDto> getTeam(){
		logger.info("with in getTeam");
		try {
			return teamRepository.getAllTeamWise();
		}catch (Exception e) {
			logger.error("Error with in getTeam :- " + e);
			return null;
		}
	}

	/**
	 * <b> Get captain list tournament wise : </b> This function returns the list of the captain tournament wise
	 * @param reportDto This is the parameter for the getCaptainListTimeStamp function
	 * @return List<ResponseCaptainListWithTeam> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	@Override
	public List<ResponseCaptainListWithTeam> getCaptainListTimeStamp(ReportDto reportDto) {
		
		logger.info("with in getCaptainListTimeStamp");
		try {
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
			
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(reportDto.getStartDate());
			calStart.set(Calendar.HOUR, 0);
			calStart.set(Calendar.MINUTE, 0);
			calStart.set(Calendar.SECOND, 0);
			calStart.set(Calendar.MILLISECOND, 0);
			Date modifiedStartDate = calStart.getTime();
			String start = formatter.format(modifiedStartDate);
			Date startDate = reportDto.getStartDate();

			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(reportDto.getEndDate());
			calEnd.set(Calendar.HOUR, 23);
			calEnd.set(Calendar.MINUTE, 59);
			calEnd.set(Calendar.SECOND, 59);
			calEnd.set(Calendar.MILLISECOND, 0);
			Date modifiedEndDate = calEnd.getTime();
			String end = formatter.format(modifiedEndDate);
			Date endDate = reportDto.getEndDate();
			try {
				startDate = format.parse(start);
				endDate = format.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			reportDto.setStartDate(startDate);
			reportDto.setEndDate(endDate);
			
			return teamRepository.getAllCaptainsForReport(reportDto.getStartDate(), reportDto.getEndDate());
			
		}catch (Exception e) {
			logger.error("Error with in getTeam :- " + e);
			return null;
		}
	}
}
