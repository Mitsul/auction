package com.cygnet.Auction.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Address;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.responseDto.ResponseAddressDto;

@Component
public interface AddressRepository extends JpaRepository<Address, String> {

	Address findByEmployee(Employee employee);

	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseAddressDto(e.addressId,e.employee.empId, e.employee.name, e.city, e.state, e.contactNo) from Address e where e.employee.empId = ?1")
	ResponseAddressDto findDetailByEmployee(String empId);

	@Transactional
	@Modifying
	@Query(value = "update Address set city =?2, state =?3, contactNo =?4 where employee=?1")
	void updateAddress(Employee emp, String city, String state, long contactNo);



}
