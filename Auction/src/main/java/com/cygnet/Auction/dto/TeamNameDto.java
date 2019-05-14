/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Dto class for TeamName
 */

package com.cygnet.Auction.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamNameDto {
	
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String teamNameId;
	
	@Size(min = 2, max = 20, message = "Something went, the size for the team name should be between 2 to 20")
	private String Name;

	public TeamNameDto(
			@Size(min = 2, max = 20, message = "Something went, the size for the team name should be between 2 to 20") String name) {
		super();
		Name = name;
	}
}
