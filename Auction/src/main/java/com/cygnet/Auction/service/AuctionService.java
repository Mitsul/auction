/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for Auction
 */

package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.AuctionDto;
import com.cygnet.Auction.dto.ReportDto;
import com.cygnet.Auction.responseDto.ResponseAllBidsByCaptains;

public interface AuctionService {

	public String playerBid(AuctionDto auctionDto);
	public List<ResponseAllBidsByCaptains> getAllBidsByCaptain(ReportDto reportDto);
	public List<ResponseAllBidsByCaptains> getBidsByCaptain(ReportDto reportDto);
}
