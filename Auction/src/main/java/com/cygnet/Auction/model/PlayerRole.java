package com.cygnet.Auction.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRole {

	@Id
	@Column(name = "playerRoleId",columnDefinition = "nvarchar(60)")
	private String playerRoleId;
	
	@Column(name = "name", columnDefinition = "nvarchar(25)")
	private String name;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy="playerRole")
	private List<Player> listPlayer = new ArrayList<>();
	
	@Version
	@Column(name = "version")
	private Integer version;

	public PlayerRole(String playerRoleId, String name) {
		super();
		this.playerRoleId = playerRoleId;
		this.name = name;
	}

	public PlayerRole(String name) {
		super();
		this.name = name;
	}
	
}
