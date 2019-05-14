/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the response dto for the AllBidsByCaptains
 */

package com.cygnet.Auction.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAllBidsByCaptains {

	private String auctionId;
	
	private String empId;
	private String empPlayerId;
	private String empName;
	
	private String capEmpId;
	private String capPlayerId;
	private String captainId;
	private String capName;
	private float bidAmt;
}
