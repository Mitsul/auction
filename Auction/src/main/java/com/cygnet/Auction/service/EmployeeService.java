package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.EmployeeDto;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;

public interface EmployeeService {

	public String addEmployee(EmployeeDto emp);
	public String login(EmployeeDto emp);
	public ResponseEmployeeDto getEmployee(String emp);
	public String updateEmp(EmployeeDto emp);
	public Employee findByEmail(String email);
	public List<Employee> getAllEmployee();
}
