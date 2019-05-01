package com.cygnet.Auction.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.config.JwtTokenUtil;
import com.cygnet.Auction.dto.EmployeeDto;
import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.repository.EmployeeRepository;
import com.cygnet.Auction.responseDto.ResponseEmployeeDto;
import com.cygnet.Auction.service.EmployeeService;
import com.cygnet.Auction.util.UuidAndTimeStamp;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private UuidAndTimeStamp uuidAndTimeStamp;
	@Autowired private JwtTokenUtil jwtTokenUtil;
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private HttpServletResponse response;
	
	 @Bean
	 public BCryptPasswordEncoder bCryptPasswordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	
	public String addEmployee(EmployeeDto emp) {
		logger.info("With in addEmployee");
		try {
			if(employeeRepository.findByEmail(emp.getEmail()) == null ) {
				emp.setEmpId(uuidAndTimeStamp.getUuid());
				emp.setPassword(bCryptPasswordEncoder().encode(emp.getPassword()));
				Employee e1 = new Employee(emp.getEmpId(), emp.getEmail(),emp.getName(), emp.getGender(),emp.getPassword(),emp.getRoles());
				employeeRepository.save(e1);
				return "Player created successfully.";
			}
			else
				return "Player already exist with this email id";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in addPlayer :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in addEmployee :- " + e);
			return "Please try again, due to exception :-" + e;
		}
	}

	public String login(EmployeeDto empDto) {
		logger.info("With on login");
		try {
			Employee emp = employeeRepository.findByEmail(empDto.getEmail());
			if(emp == null) {
				return null;
			}
			else if(loginpasswordEncoder().matches(empDto.getPassword(), emp.getPassword())){
				String token = jwtTokenUtil.generateToken(emp);
				System.out.println("Login Token: "+token);
				response.addHeader("Authorization", "Bearer "+token);
				response.addHeader("ROLE", emp.getRoles());
				response.addHeader("EMPID", emp.getEmpId());
				System.out.println("user in login "+emp.toString());
				final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
						empDto.getEmail(),
						empDto.getPassword()
					)
				);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				return token;
			}
			else {
				System.out.println("Bad crenditials");
				return null;
			}
		}catch (Exception e) {
			logger.error("Error with in login :- " + e);
			return null;
		}		
	}

	public ResponseEmployeeDto getEmployee(String empId) {
		logger.info("With in getemployee");
		try {
			return employeeRepository.findDetailById(empId);
		}catch (Exception e) {
			logger.error("Error with in getEmployee :- " + e);
			return null;
		}
	}
	
	
	@Bean
	@Primary
	public PasswordEncoder loginpasswordEncoder() {
	return new BCryptPasswordEncoder();
	}
	

	public String updateEmp(EmployeeDto emp) {
		logger.info("With in updateEMp");
		try {
			employeeRepository.updateData(emp.getEmpId(),emp.getEmail(),emp.getName(),emp.getGender(),emp.getRoles());
			return "Details updated successfully.";
		}catch (OptimisticLockException | StaleObjectStateException | HibernateOptimisticLockingFailureException e) {
			logger.error("Error with in updateEmp :- " + e);
			return "Please try again, due to exception of locking :-" + e;
		}catch (Exception e){
			logger.error("Error with in updateEmp :- " + e);
			return "Please try again, due to exception :-\" + e";
		}
	}

	@Override
	public Employee findByEmail(String email) {
		logger.info("With in findByEmail");
		try {
			return employeeRepository.findByEmail(email);
		}catch (Exception e) {
			logger.error("Error with in findByEmail :- " + e);
			return null;
		}
	}

	@Override
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

}
