package com.cygnet.Auction.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;

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
@Table(name = "Bidding")
public class Bidding {
	
	@Id
	@Column(name = "biddingId",columnDefinition = "nvarchar(60)")
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String biddingId;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "capId")
	private Captain captain;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "playerId")
	private Player player;
	
	@Column(name = "currBidPrice",columnDefinition = "float(7)")
	private float currBidPrice;
	@Column(name = "biddingDatetime",columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date biddingDatetime;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL,mappedBy="bidding")
	private List<Team_Allocation> listTeamAllocation = new ArrayList<>();

	@Version
	@Column(name = "version")
	private Integer version;
	
	public Bidding(String biddingId, Captain captain, Player player, float currBidPrice, Date biddingDatetime) {
		super();
		this.biddingId = biddingId;
		this.captain = captain;
		this.player = player;
		this.currBidPrice = currBidPrice;
		this.biddingDatetime = biddingDatetime;
	}

	public Bidding(String biddingId, Captain captain, Player player, float currBidPrice, Date biddingDatetime,
			List<Team_Allocation> listTeamAllocation) {
		super();
		this.biddingId = biddingId;
		this.captain = captain;
		this.player = player;
		this.currBidPrice = currBidPrice;
		this.biddingDatetime = biddingDatetime;
		this.listTeamAllocation = listTeamAllocation;
	}
}