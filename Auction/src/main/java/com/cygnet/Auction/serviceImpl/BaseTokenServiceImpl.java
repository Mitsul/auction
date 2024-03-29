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

import com.cygnet.Auction.dto.BaseTokenDto;
import com.cygnet.Auction.model.BaseToken;
import com.cygnet.Auction.model.Player;
import com.cygnet.Auction.repository.BaseTokenRepository;
import com.cygnet.Auction.repository.PlayerRepository;
import com.cygnet.Auction.service.BaseTokenService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class BaseTokenServiceImpl implements BaseTokenService{
	
	static Logger logger = LoggerFactory.getLogger(BaseTokenServiceImpl.class);

	@Autowired private BaseTokenRepository baseTokenRepository;
	@Autowired private PlayerRepository playerRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	
	
	/**
	 * <b> Generate Base Token : </b> This function is for generating the base token for the players
	 * @param baseTokenDto This is the parameter for the generateBaseTokenForAllPlayers function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String generateBaseTokenForAllPlayers(BaseTokenDto baseTokenDto) {
		logger.info("With in generateTokenForAllPlayers");
		try {
			List<Player> playerList = playerRepository.getPlayersForBid(0);
			for(int i = 0 ; i < playerList.size() ; i++) {
				Player player = playerRepository.findByPlayerId(playerList.get(i).getPlayerId());
				BaseToken baseToken = new BaseToken(uuidAndTimeStamp.getUuid(),player,baseTokenDto.getBaseToken());
				baseTokenRepository.save(baseToken);
			}
			return "Base Token Generated Successfully";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Wrror with in generateTokenForAllPlayers :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in generateTokenforAllPlayers :- " + e);
			return "Please try again, due to exception :-\" + e";
		}
	}

}
