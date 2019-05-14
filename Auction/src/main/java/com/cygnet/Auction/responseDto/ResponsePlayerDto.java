/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the response dto for the player
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
public class ResponsePlayerDto {

	private String playerId;
	private String empId;
	private String email;
	private String name;
	private String gender;
	private int isActive;
	private int prefCaptain;
	private String playerRole;
	private String playerRoleId;
}
