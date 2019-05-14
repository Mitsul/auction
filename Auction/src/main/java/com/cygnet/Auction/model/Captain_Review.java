/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Model class for Captain_Review
 */

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
@Table(name = "Captain_Review")
public class Captain_Review {

	@Id
	@Column(name = "capRevId",columnDefinition = "nvarchar(60)")
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String capRevId;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "capPrefId")
	private Player player_cap_ref;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "playerId")
	private Player player;
	
	@Column(name = "rating",columnDefinition = ("int(1)"))
	private int rating;
	@Column(name = "captainReviewDatetime",columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date captainReviewDatetime;
	
	@Version
	@Column(name = "version")
	private Integer version;
	
	public Captain_Review(String capRevId, Player player_cap_ref, Player player, int rating,
			Date captainReviewDatetime) {
		super();
		this.capRevId = capRevId;
		this.player_cap_ref = player_cap_ref;
		this.player = player;
		this.rating = rating;
		this.captainReviewDatetime = captainReviewDatetime;
	}
}