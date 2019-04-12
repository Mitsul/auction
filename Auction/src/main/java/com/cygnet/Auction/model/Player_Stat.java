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
@Table(name = "Player_Stat")
public class Player_Stat{

	@Id
	@Column(name = "playerStatId",columnDefinition = "nvarchar(60)")
	private String playerStatId;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "empId")
	private Employee employee;
	
	@Column(name = "totalRuns",columnDefinition = "int(255)")
	private int totalRuns;
	@Column(name = "totalWick",columnDefinition = "int(50)")
	private int totalWick;
	@Column(name = "manOfTheMatch",columnDefinition = "int(10)")
	private int manOfTheMatch;
	@Column(name = "CreationDatetime",columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date CreationDatetime;
	@Column(name = "UpdationDatetime",columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date UpdationDatetime;
	
	@Version
	@Column(name = "version")
	private Integer version;
	
	public Player_Stat(String playerStatId, int totalRuns, int totalWick, int manOfTheMatch, Date updationDatetime) {
		super();
		this.playerStatId = playerStatId;
		this.totalRuns = totalRuns;
		this.totalWick = totalWick;
		this.manOfTheMatch = manOfTheMatch;
		UpdationDatetime = updationDatetime;
	}

	public Player_Stat(String playerStatId, Employee employee, int totalRuns, int totalWick, int manOfTheMatch,
			Date creationDatetime, Date updationDatetime) {
		super();
		this.playerStatId = playerStatId;
		this.employee = employee;
		this.totalRuns = totalRuns;
		this.totalWick = totalWick;
		this.manOfTheMatch = manOfTheMatch;
		CreationDatetime = creationDatetime;
		UpdationDatetime = updationDatetime;
	}
}