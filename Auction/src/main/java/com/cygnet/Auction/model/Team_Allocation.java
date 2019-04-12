package com.cygnet.Auction.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.context.annotation.Configuration;

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
@Table(name = "Team_Allocaion")
public class Team_Allocation {

	@Id
	@Column(name = "teamAllId",columnDefinition = "nvarchar(60)")
	private String teamAllId;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "teamId")
	private Team team;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "playerId")
	private Player player;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "bidId")
	private Bidding bidding;
	
	@Column(name = "teamAllocationDatetime",columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date teamAllocationDatetime;

	@Version
	@Column(name = "version")
	private Integer version;
	
	public Team_Allocation(String teamAllId, Team team, Player player, Bidding bidding, Date teamAllocationDatetime) {
		super();
		this.teamAllId = teamAllId;
		this.team = team;
		this.player = player;
		this.bidding = bidding;
		this.teamAllocationDatetime = teamAllocationDatetime;
	}
}