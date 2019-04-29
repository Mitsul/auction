package com.cygnet.Auction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "User")
public class User {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "fname")
	private String fname;
	@Column(name = "lname")
	private String lname;

}
