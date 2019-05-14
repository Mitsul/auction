/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the implementation class of the TokenService class
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

import com.cygnet.Auction.model.Captain;
import com.cygnet.Auction.model.Token;
import com.cygnet.Auction.repository.CaptainRepository;
import com.cygnet.Auction.repository.TokenRepository;
import com.cygnet.Auction.responseDto.ResponseCaptainTokenDto;
import com.cygnet.Auction.service.TokenService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class TokenServiceImpl implements TokenService{
	
	static Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Autowired private TokenRepository tokenRepository;
	@Autowired private CaptainRepository captainRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	
	/**
	 * <b> Get Tokens : </b> This function is for getting the allocated tokens and the remaining tokens
	 * @return List<ResponseCaptainTokenDto> This is the return of the function
	 * @exception e This are the exceptions for the function
	 * @see e
	 */
	public List<ResponseCaptainTokenDto> getTokens() {
		logger.info("With in getTokens");
		try {
			return tokenRepository.captainToken();
		}catch (Exception e) {
			logger.error("Error with in getTokens :- " + e);
			return null;
		}
		
	}

	/**
	 * <b> Generate Tokens : </b> This function is for allocating the tokens to the captains
	 * @param tokens This is the parameter for the generateTokens function
	 * @return String This is the return of the function
	 * @exception OptimisticLockException,StaleObjectStateException,HibernateOptimisticLockingFailureException, e This are the exceptions for the function
	 * @see OptimisticLockException
	 * @see StaleObjectStateException
	 * @see HibernateOptimisticLockingFailureException
	 * @see e
	 */
	public String generateTokens(float tokens) {
		logger.info("With in generateTokens");
		try {
			List<Captain> captainList = captainRepository.findAll();
			List<Token> tokenList = tokenRepository.findAll();
			if(tokenList.isEmpty()) {
				for(int i = 0 ; i < captainList.size() ; i++) {
					Captain captain = captainRepository.findByCapId(captainList.get(i).getCapId());
					Token token = new Token(uuidAndTimeStamp.getUuid(),captain,tokens, tokens);
					tokenRepository.save(token);
				}
				return "Tokens generated successfully for captains";
			}
			else
				return "Tokens already generated for captains";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in generateTokens :- " + e);
			return "Please try again :-" + e;
		}catch (Exception e){
			logger.error("Error with in geenrateTokens :- " + e);
			return "Please try again, due to exception :- " + e;
		}
	}
}
