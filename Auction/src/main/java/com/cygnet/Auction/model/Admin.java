package com.cygnet.Auction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "Admin")
public class Admin {

	@Id
	@Column(name = "adminId", columnDefinition = "nvarchar(60)")
	private String id;
	@Column(name = "name",columnDefinition = "varchar(45)")
	private String name;
	@Column(name = "email",columnDefinition = "nvarchar(60)")
	private String email;
	@Column(name = "password",columnDefinition = "nvarchar(255)")
	private String password;
	
	@Version
	@Column(name = "version")
	private Integer version;
	
	public Admin(String id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
