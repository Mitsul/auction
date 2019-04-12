package com.cygnet.Auction.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Team")
public class Team {
	
	@Id
	@Column(name = "teamId",columnDefinition = "nvarchar(60)")
	private String teamId;

	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "teamNameId")
	private TeamName teamname;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "capId")
	private Captain captain;
	
	@Column(name = "teamDateTime",columnDefinition ="datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date teamDatetime;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="team")
	private List<Team_Allocation> listTeamAllocation = new ArrayList<>();

	@Version
	@Column(name = "version")
	private Integer version;
	
	public Team(String teamId, TeamName teamName, Captain captain, Date teamDatetime) {
		super();
		this.teamId = teamId;
		this.teamname = teamName;
		this.captain = captain;
		this.teamDatetime = teamDatetime;
	}

	public Team(String teamId, TeamName teamname, Captain captain, Date teamDatetime,
			List<Team_Allocation> listTeamAllocation) {
		super();
		this.teamId = teamId;
		this.teamname = teamname;
		this.captain = captain;
		this.teamDatetime = teamDatetime;
		this.listTeamAllocation = listTeamAllocation;
	}
	
}
