/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Dto class for Player
 */

package com.cygnet.Auction.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

	@Size(min = 36, max = 36, message = "Something went please try again")
	private String playerId;
	
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String empId;
	
	private int prefCaptain;
	private int isActive;
	private Date joinedOn;
	private Date updatedOn;
	
	@Size(min = 36, max = 36, message = "Something went with player Role, please try again")
	private String playerRole;

	public PlayerDto(@Size(min = 36, max = 36, message = "Something went please try again") String empId,
			int prefCaptain, int isActive,
			@Size(min = 6, max = 36, message = "Something went with player Role, please try again") String playerRole) {
		super();
		this.empId = empId;
		this.prefCaptain = prefCaptain;
		this.isActive = isActive;
		this.playerRole = playerRole;
	}

	@Override
	public String toString() {
		return "PlayerDto [playerId=" + playerId + ", empId=" + empId + ", prefCaptain=" + prefCaptain + ", isActive="
				+ isActive + ", joinedOn=" + joinedOn + ", updatedOn=" + updatedOn + ", playerRole=" + playerRole + "]";
	}
	
	
}