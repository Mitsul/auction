package com.cygnet.Auction.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Employee")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "seq", initialValue=11, allocationSize=500)
public class Employee{

	@Id
	@Column(name = "empId", columnDefinition = "nvarchar(60)", unique = true)
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String empId;
	
	@Column(name = "email", columnDefinition = "varchar(50)", unique = true)
	@Email
	private String email;
	
	@Column(name = "name", columnDefinition = "varchar(45)")
	@Size(min = 3, max = 25, message = "The length of the name should be between 3 to 25")
	private String name;
	
	@Column(name = "gender", columnDefinition = "varchar(6)")
	@Size(min = 4, max = 6, message = "Something went wrong with the gender, please try again")
	private String gender;
	
	@Column(name = "password", columnDefinition = "nvarchar(255)")
	private String password;

	@Column(name = "roles", columnDefinition = "nvarchar(25)")
	@Size(min = 10, max = 13, message = "Something went with the role, please try again")
	private String roles;
	
	@JsonBackReference
	@OneToMany(mappedBy="employee",cascade=CascadeType.ALL)
	private List<Player_Stat> list_playerStat = new ArrayList<>();
	
	@JsonBackReference
	@OneToOne(mappedBy="employee",fetch = FetchType.LAZY)
	private Player player;

	@JsonBackReference
	@OneToOne(mappedBy="employee",fetch = FetchType.LAZY)
	private Address address;
	
	@Version
	@Column(name = "version")
	private Integer version;
	
	
	public Employee(String empId, String email, String name, String gender) {
		super();
		this.empId = empId;
		this.email = email;
		this.name = name;
		this.gender = gender;
	}
	
	public Employee(String empId, String email, String name, String gender, String password, String roles) {
		super();
		this.empId = empId;
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.roles = roles;
	}

	public Employee(String empId, String email, String name, String gender, String password,
			List<Player_Stat> list_playerStat, Player player, Address address) {
		super();
		this.empId = empId;
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.list_playerStat = list_playerStat;
		this.player = player;
		this.address = address;
	}

}
