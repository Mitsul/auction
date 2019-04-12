package com.cygnet.Auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.dto.AdminDto;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.service.AdminService;

@RestController
public class AdminController {

	@Autowired AdminService adminService;

	// add emp by admin /admin/addEmployee
	// refer public void addEmployee() in EmployeeController
	
	// get detail of a particular emp by admin /admin/getEmployee
	//refer public Optional<Employee> empData() in EmployeeController

	// update emp detail's by admin /admin/update/Emloyee
	// refer public void updateEmp() in EmployeeController
	
	@GetMapping(value = "/admin/getAllEmployee")
	public List<Employee> getAllEmployee() {
		return adminService.getAllEmployee();
	}
	
	@PostMapping(value = "/admin/login")
	public String loginAdmin(@RequestBody AdminDto adminDto) {
		return adminService.loginAdmin(adminDto);
	}

	@PostMapping(value = "/admin/addAdmin")
	public String addAdmin(@RequestBody AdminDto adminDto) {
		return adminService.addAdmin(adminDto);
	}
}
