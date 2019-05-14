/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for Bidding
 */

package com.cygnet.Auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.service.BiddingService;

@RestController
public class BiddingController {

	@Autowired
	BiddingService biddingService;
}
