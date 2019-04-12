package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.Captain_ReviewDto;
import com.cygnet.Auction.responseDto.ResponseGetCaptainReview;
import com.cygnet.Auction.responseDto.ResponsePlayersWithCapPrefDto;

public interface Captain_ReviewService {

	public List<ResponsePlayersWithCapPrefDto> getCaptainPrefList();
	public String giveReview(Captain_ReviewDto captain_ReviewDto);
	public ResponseGetCaptainReview getReview(String empId);
	public String updateReview(Captain_ReviewDto captain_ReviewDto);
}
