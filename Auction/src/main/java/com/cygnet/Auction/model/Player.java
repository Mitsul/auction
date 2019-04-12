package com.cygnet.Auction.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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
@Table(name = "Player")
@NamedQuery(name = "Player.getPlayersForBid" , query = "select p from Player p where prefCaptain = :flag")
@NamedQuery(name = "Player.getPlayersForBidInAuction" , query = "select new com.cygnet.Auction.responseDto.responsePlayersForBid(p.employee.empId , p.playerId, p.employee.name,coalesce(b.captain.capId,'N/A') as lastBidder , coalesce(b.currBidPrice, 0.0) as lastBidderAmt, coalesce(p.playerRole.name,'N/A') as player) from Player p left join Bidding b on p.playerId = b.player.playerId where p.prefCaptain = :flag and (b.currBidPrice is null or b.currBidPrice > 0.0) and (b.captain.capId is null or b.captain.capId is not null) and (p.playerRole is null or p.playerRole is not null)")
public class Player{
	
	@Id
	@Column(name = "playerId", columnDefinition = "nvarchar(60)")
	private String playerId;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "empId", unique=true)
	private Employee employee;
	
	@Column(name = "preferenceCaptain", columnDefinition = "int(1)")
	private int prefCaptain;
	@Column(name = "isActive", columnDefinition = "int(1)")
	private int isActive;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "joinedOn", columnDefinition = "datetime")
	private Date joinedOn;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedOn", columnDefinition = "datetime")
	private Date updatedOn;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "playerRole")
	private PlayerRole playerRole;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL ,mappedBy="player")
	private List<Auction> auctionList = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="player")
	private List<Bidding> list_bidding = new ArrayList<>();
	
	@JsonBackReference
	@OneToOne(mappedBy="player")
	private Captain_Review captainReview;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="player_cap_ref")
	private List<Captain_Review> captainReviewList = new ArrayList<>();
	
	@JsonBackReference
	@OneToOne(mappedBy="player",fetch = FetchType.LAZY)
	private Captain captain;

	@JsonBackReference
	@OneToOne(mappedBy="player")
	private Team_Allocation teamAllocation;
	
	@JsonBackReference
	@OneToOne(mappedBy="player")
	private BaseToken token;

	@Version
	@Column(name = "version")
	private Integer version;
	
	public Player(String playerId, Employee employee, int prefCaptain, int isActive,
			Date joinedOn, Date updatedOn, PlayerRole playerRole) {
		super();
		this.playerId = playerId;
		this.employee = employee;
		this.prefCaptain = prefCaptain;
		this.isActive = isActive;
		this.joinedOn = joinedOn;
		this.updatedOn = updatedOn;
		this.playerRole = playerRole;
		
	}

	public Player(String playerId, Employee employee, int prefCaptain, int isActive, Date joinedOn, Date updatedOn,
			PlayerRole playerRole, List<Auction> auctionList, List<Bidding> list_bidding, Captain_Review captainReview,
			List<Captain_Review> captainReviewList, Captain captain, Team_Allocation teamAllocation, BaseToken token) {
		super();
		this.playerId = playerId;
		this.employee = employee;
		this.prefCaptain = prefCaptain;
		this.isActive = isActive;
		this.joinedOn = joinedOn;
		this.updatedOn = updatedOn;
		this.playerRole = playerRole;
		this.auctionList = auctionList;
		this.list_bidding = list_bidding;
		this.captainReview = captainReview;
		this.captainReviewList = captainReviewList;
		this.captain = captain;
		this.teamAllocation = teamAllocation;
		this.token = token;
	}
	
}