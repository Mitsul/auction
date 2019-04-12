package com.cygnet.Auction.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "Token")

public class Token {
	
	@Id
	@Column(name = "tokenId",columnDefinition = "nvarchar(60)")
	private String tokenId;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "capId")
	private Captain captain;
	
	@Column(name = "tokens" ,columnDefinition = "float(7)")
	private float tokens;
	
	@Column(name = "remainingTokens", columnDefinition = "float(7)")
	private float remainingTokens;

	@Version
	@Column(name = "version")
	private Integer version;
	
	public Token(String tokenId, Captain captain, float tokens, float remainingTokens) {
		super();
		this.tokenId = tokenId;
		this.captain = captain;
		this.tokens = tokens;
		this.remainingTokens = remainingTokens;
	}
}
