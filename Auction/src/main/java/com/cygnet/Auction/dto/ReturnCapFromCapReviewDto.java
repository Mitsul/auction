/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Response Dto class for CaptainFromCaptainReview
 */

package com.cygnet.Auction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnCapFromCapReviewDto {

	String empId;
	String playerId;
	String name;
	double avgRating;
}
