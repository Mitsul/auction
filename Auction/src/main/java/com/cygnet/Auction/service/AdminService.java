package com.cygnet.Auction.service;

import java.util.List;

import com.cygnet.Auction.dto.AdminDto;
import com.cygnet.Auction.model.Employee;

public interface AdminService {

	public List<Employee> getAllEmployee();
	public String loginAdmin(AdminDto adminDto);
	public String addAdmin(AdminDto adminDto);
}
