/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> This class is the service class for Employee
 */

package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.EmployeeDto;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;
import com.cygnet.Auction.responseDto.ResponseNumericDto;

public interface EmployeeService {

	public String addEmployee(EmployeeDto emp);
	public String login(EmployeeDto emp);
	public ResponseEmployeeDto getEmployee(String emp);
	public String updateEmp(EmployeeDto emp);
	public Employee findByEmail(String email);
	public List<Employee> getAllEmployee();
	public ResponseNumericDto getTotalEmployeesCount();
}
