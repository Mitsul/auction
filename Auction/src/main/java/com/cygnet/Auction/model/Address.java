package com.cygnet.Auction.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Address")
public class Address{

	@Id
	@Column(name = "addressId", columnDefinition = "nvarchar(60)", nullable=false, unique=true)
	@Size(min = 36, max = 36, message = "Something went please try again")
	private String addressId;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "empId", nullable=false)
//	@Size(min = 36, max = 36, message = "Something went please try again")
	private Employee employee;
	
	@Column(name = "city",columnDefinition = "varchar(45)")
	@Size(min = 2, max = 20)
	private String city;
	
	@Column(name = "state",columnDefinition = "varchar(45)")
	@Size(min = 2, max = 20)
	private String state;
	
	@Column(name = "contactNo",columnDefinition = "numeric(10)")
	private long contactNo;
	
	@Version
	@Column(name = "version")
	private Integer version;
	
	public Address(String addressId, String city, String state, long contactNo) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.state = state;
		this.contactNo = contactNo;
	}

	public Address(String addressId, Employee employee, String city, String state, long contactNo) {
		super();
		this.addressId = addressId;
		this.employee = employee;
		this.city = city;
		this.state = state;
		this.contactNo = contactNo;
	}
	
}
