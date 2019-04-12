package com.cygnet.Auction.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.dto.AdminDto;
import com.cygnet.Auction.model.Admin;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.repository.AdminRepository;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.service.AdminService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class AdminServiceImpl implements AdminService{
	
	private final static Logger logger = Logger.getLogger(AdminServiceImpl.class);

	@Autowired private AdminRepository adminRepository;
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	
	public List<Employee> getAllEmployee() {
		logger.info("With in getAllEmployee");
		try {
			List<Employee> allEmp = new ArrayList<>();
			employeeRepository.findAll().forEach(allEmp :: add);
			return allEmp;
		}catch (Exception e) {
			logger.error("Error with in getAllEmployees :- " + e);
			return null; 
		}
	}

	public String loginAdmin(AdminDto adminDto) {
		logger.info("With in loginAdmin");
		try {
			Admin admin = adminRepository.findByEmail(adminDto.getEmail());
			if(admin == null)
				return "Please Enter Valid Mail Address";
			else {
				Admin admin_data = adminRepository.findByEmailAndPassword(adminDto.getEmail(), adminDto.getPassword());
				if(admin_data == null)
					return "Please enter valid password";
				else
					return admin_data.getId();
			}
		}catch (Exception e) {
			logger.error("Error with in loginAdmin :- " + e);
			return "Something went wrong, please try again.";
		}
	}

	public String addAdmin(AdminDto adminDto) {
		logger.info("With in addAdmin");
		try {
			adminDto.setId(uuidAndTimeStamp.getUuid());
			Admin admin = new Admin(adminDto.getId(),adminDto.getName(),adminDto.getEmail(),adminDto.getPassword());
			adminRepository.save(admin);
			return "Admin created successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in addAdmin :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in addAdmin :- " + e);
			return "Please try again, due to exception :-\" + e";
		}
		
	}

}
