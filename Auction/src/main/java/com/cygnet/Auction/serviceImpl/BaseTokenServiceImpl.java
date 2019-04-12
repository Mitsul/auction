package com.cygnet.Auction.serviceImpl;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
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
	
	private final static Logger logger = Logger.getLogger(BaseTokenServiceImpl.class);

	@Autowired private BaseTokenRepository baseTokenRepository;
	@Autowired private PlayerRepository playerRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	
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
