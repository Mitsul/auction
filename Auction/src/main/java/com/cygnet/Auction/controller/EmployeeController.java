/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Controller class for Employee
 */

package com.cygnet.Auction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.EmployeeDto;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;
import com.cygnet.Auction.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired EmployeeService employeeService;

	@CrossOrigin(origins="/**", exposedHeaders= {"Authorization","EMPID"})
	@PostMapping(value = "/employee/login/")
	public ResponseEntity<String> login(@RequestBody EmployeeDto emp) {
		if(employeeService.login(emp) == null) {
			return new ResponseEntity<String>("Bad crenditials",HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}	
	}
	
	/**
	 * <b> Add Employee : </b> This function is for adding the new employee
	 * @param emp Input type of the function addEmployee
	 * @param err Input type of the function addEmployee
	 * @return String
	 */
	@PostMapping(value = "/admin/addEmployee", produces = "text/plain")
	public String addEmployee(@Valid @RequestBody EmployeeDto emp, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return employeeService.addEmployee(emp);
	}
	
	/**
	 * <b> Get Employee : </b> This function returns the employee by Id
	 * @param empId Input type of the function getEmpData
	 * @return ResponseEmployeeDto
	 */
	@GetMapping(value= {"/admin/{empId}" ,"/employee/{empId}", "/admin/getEmployee/{empId}"})
	public ResponseEmployeeDto getEmpData(@PathVariable("empId") String empId) {
		return employeeService.getEmployee(empId);
	}
	
	/**
	 * <b> Update Employee : </b> This function is for updating the emp
	 * @param emp Input type of the function updateEmp
	 * @param err If the input type is failed as per the validation
	 * @return String
	 */
	@PutMapping(value= {"admin/update" ,"/employee/update", "/admin/update/Employee"})
	public String updateEmp(@Valid @RequestBody EmployeeDto emp, Errors err) {
		if(err.hasErrors())
			return err.getFieldError().getField() + " " + err.getFieldError().getDefaultMessage();
		else
			return employeeService.updateEmp(emp);
	}
	
	/**
	 * <b> List of Employee : </b> This function returns the list of employees
	 * @return List of Employee
	 */
	@GetMapping(value = "/admin/getAllEmployee")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}
}