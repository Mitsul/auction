package com.cygnet.Auction.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BaseToken")
public class BaseToken {

	@Id
	@Column(name = "baseTokenId", columnDefinition = "nvarchar(60)")
	private String baseTokenId;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "playerId", unique=true)
	private Player player;
	
	@Column(name = "baseToken", columnDefinition = "float(7)")
	private float baseToken;

	@Version
	@Column(name = "version")
	private Integer version;
	
	public BaseToken(String baseTokenId, Player player, float baseToken) {
		super();
		this.baseTokenId = baseTokenId;
		this.player = player;
		this.baseToken = baseToken;
	}
}
