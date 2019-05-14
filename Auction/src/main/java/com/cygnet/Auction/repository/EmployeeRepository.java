/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Repository class for Employee
 */

package com.cygnet.Auction.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;

@Component
public interface EmployeeRepository extends CrudRepository<Employee, String> {

	Employee findByEmailAndPassword(String email,String password);
	Employee findByEmail(String email);
	Optional<Employee> findById(String id);
	Employee findByEmpId(String emp_id); // for player service
	
	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseEmployeeDto(e.empId,e.email,e.name,e.gender,e.roles) from Employee e")
	List<ResponseEmployeeDto> findAllEmpData();
	
	@Query(value = "select new com.cygnet.Auction.responseDto.ResponseEmployeeDto(e.empId,e.email,e.name,e.gender,e.roles) from Employee e where empId =?1")
	ResponseEmployeeDto findDetailById(String empId);
	
	@Transactional
	@Modifying
	@Query(value = "update Employee set email =?2, name =?3, gender =?4 where empId =?1")
	void updateData(String empId, String email, String name, String gender);
}