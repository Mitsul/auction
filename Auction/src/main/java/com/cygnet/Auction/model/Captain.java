package com.cygnet.Auction.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

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
@Table(name = "Captain")
public class Captain {
	
	@Id
	@Column(name = "capId",columnDefinition = "nvarchar(60)")
	private String capId;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "playerId", nullable=false)
	private Player player;
	
	@Column(name = "captainDatetime",columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date captainDatetime;
	
	@JsonBackReference
	@OneToOne(mappedBy="captain")
	private Token token;
	
	@JsonBackReference
	@OneToOne(mappedBy="captain")
	private Team team;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="captain")
	private List<Bidding> listBidding = new ArrayList<>();

	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="captain")
	private List<Auction> listAuction = new ArrayList<>();

	@Version
	@Column(name = "version")
	private Integer version;
	
	public Captain(String capId, Player player, Date captainDatetime) {
		super();
		this.capId = capId;
		this.player = player;
		this.captainDatetime = captainDatetime;
	}

	public Captain(String capId, Player player, Date captainDatetime, Token token, Team team, List<Bidding> listBidding,
			List<Auction> listAuction) {
		super();
		this.capId = capId;
		this.player = player;
		this.captainDatetime = captainDatetime;
		this.token = token;
		this.team = team;
		this.listBidding = listBidding;
		this.listAuction = listAuction;
	}
	
}