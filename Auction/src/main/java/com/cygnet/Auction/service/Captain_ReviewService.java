/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for Captain_Review
 */

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
