/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the response dto for the Player_Stat
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
public class ResponsePlayer_StatDto {

	private String playerStatId;
	private String empId;
	private String name;
	private String gender;
	private int totalRuns;
	private int totalWick;
	private int manOfTheMatch;
}
