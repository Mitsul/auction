/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Dto class for PlayerRole
 */

package com.cygnet.Auction.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRoleDto {

	@Size(min = 36, max = 36, message = "Something went please try again")
	private String playerRoleId;
	
	@Size(min = 6, max = 25, message = "Something went please, the length of the player role should be between 6 to 25")
	private String name;

	public PlayerRoleDto(
			@Size(min = 6, max = 25, message = "Something went please, the length of the player role should be between 6 to 25") String name) {
		super();
		this.name = name;
	}
}
