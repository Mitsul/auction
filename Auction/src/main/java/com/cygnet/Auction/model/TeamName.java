package com.cygnet.Auction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TeamName")
public class TeamName {

	
	@Id
	@Column(name = "teamNameId",columnDefinition = "nvarchar(60)")
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String teamNameId;
	
	@Column(name = "Name",columnDefinition = "varchar(45)")
	@Size(min = 2, max = 20, message = "Something went, the size for the team name should be between 2 to 20")
	private String Name;
	
	@JsonBackReference
	@OneToOne(mappedBy="teamname", fetch = FetchType.LAZY)
	private Team team;

	@Version
	@Column(name = "version")
	private Integer version;
	
	public TeamName(String teamNameId, String name) {
		super();
		this.teamNameId = teamNameId;
		Name = name;
	}

	public TeamName(String teamNameId, String name, Team team) {
		super();
		this.teamNameId = teamNameId;
		Name = name;
		this.team = team;
	}
}
