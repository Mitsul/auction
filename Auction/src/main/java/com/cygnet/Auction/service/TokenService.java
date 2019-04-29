package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.responseDto.ResponseCaptainTokenDto;

public interface TokenService {

	public List<ResponseCaptainTokenDto> getTokens();
	public String generateTokens(float tokens);
}
