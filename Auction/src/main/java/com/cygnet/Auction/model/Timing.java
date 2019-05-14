/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Model class for Timing
 */

package com.cygnet.Auction.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.context.annotation.Configuration;

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
@Table(name = "Timing")
public class Timing {

	@Id
	@Column(name = "timeId", columnDefinition = "nvarchar(60)")
	private String timeId;
	
	@Column(name = "participateStartDate", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date participateStartDate;	
	
	@Column(name = "participateEndDate", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date participateEndDate;

	@Column(name = "reviewStartDate", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reviewStartDate;
	
	@Column(name = "reviewEndDate", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reviewEndDate;
	
	@Column(name = "captainListDate", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date captainListDate;
	
	@Column(name = "auctionStartDate", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionStartDate;
	
	@Column(name = "auctionEndDate", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionEndDate;

	@Version
	@Column(name = "version")
	private Integer version;
	
	public Timing(String timeId, Date participateStartDate, Date participateEndDate, Date reviewStartDate,
			Date reviewEndDate, Date captainListDate, Date auctionStartDate, Date auctionEndDate) {
		super();
		this.timeId = timeId;
		this.participateStartDate = participateStartDate;
		this.participateEndDate = participateEndDate;
		this.reviewStartDate = reviewStartDate;
		this.reviewEndDate = reviewEndDate;
		this.captainListDate = captainListDate;
		this.auctionStartDate = auctionStartDate;
		this.auctionEndDate = auctionEndDate;
	}
		
}