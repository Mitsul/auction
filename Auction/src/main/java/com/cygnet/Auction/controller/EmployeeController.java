package com.cygnet.Auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.EmployeeDto;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;
import com.cygnet.Auction.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired EmployeeService employeeService;
	
	@PostMapping(value = "/admin/addEmployee")
	public String addEmployee(@RequestBody EmployeeDto emp) {
		return employeeService.addEmployee(emp);
	}
	
	@CrossOrigin(origins="/**", exposedHeaders= {"Authorization","EMPID"})
	@PostMapping(value = "/employee/login/")
	public ResponseEntity<String> login(@RequestBody EmployeeDto emp) {
		System.out.println("Emp Dto" + emp.toString());
		System.out.println("Emp Dto" + emp.toString() + " Status " + employeeService.login(emp));
		if(employeeService.login(emp) == null) {
			return new ResponseEntity<String>("Bad crenditials",HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}	
	}
	
	@GetMapping(value= {"/employee/{empId}", "/admin/getEmployee/{empId}"})
	public ResponseEmployeeDto getEmpData(@PathVariable("empId") String empId) {
		return employeeService.getEmployee(empId);
	}
	
	@PutMapping(value= {"/employee/update", "/admin/update/Emloyee"})
	public String updateEmp(@RequestBody EmployeeDto emp) {
		return employeeService.updateEmp(emp);
	}
}