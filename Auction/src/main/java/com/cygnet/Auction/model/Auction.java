package com.cygnet.Auction.model;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;

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
@Table(name = "Auction")
public class Auction{
	
	@Id
	@Column(name = "auctionId",columnDefinition = "nvarchar(60)")
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String auctionId;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "playerId")
	private Player player;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "lastBidder")
	private Captain captain;
	
	@Column(name = "lastBidAmt",columnDefinition = "float(7)")
	private float lastBidAmt;
	@Column(name = "auctionDatetime",columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionDatetime;
	
	@Version
	@Column(name = "version")
	private Integer version;
	
	public Auction(String auctionId, Player player, Captain captain, float lastBidAmt, Date auctionDatetime) {
		super();
		this.auctionId = auctionId;
		this.player = player;
		this.captain = captain;
		this.lastBidAmt = lastBidAmt;
		this.auctionDatetime = auctionDatetime;
	}
}
