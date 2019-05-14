/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the response dto for the Timing
 */

package com.cygnet.Auction.responseDto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTimingDto {

	private String timeId;
	private Date participateStartDate;
	private Date participateEndDate;
	private Date reviewStartDate;
	private Date reviewEndDate;
	private Date captainListDate;
	private Date auctionStartDate;
	private Date auctionEndDate;
		
	
	
}
