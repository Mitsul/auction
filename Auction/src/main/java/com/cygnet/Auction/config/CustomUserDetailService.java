/**
 * @author Mitsul
 * @version 1.0
 * @since 1.8
 * 
 * <b>Desc	: </b> Config class for CustomUserDetailService
 */

package com.cygnet.Auction.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Employee;
import com.cygnet.Auction.repository.EmployeeRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public CustomUserDetailService(EmployeeRepository userAdminRepository) {
		this.employeeRepository = userAdminRepository;
	}
	
	Employee emp = new Employee();
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Employee user = employeeRepository.findByEmail(username);
	
	if(user==null && equals(user)==false ) 
	{
		new UsernameNotFoundException("User not found");
		return null;	
	}
	else
	{
		emp = user;
		System.out.println("loadUserByUsername "+user.toString()+" Role: "+user.getRoles());
		List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
		List<GrantedAuthority> authorityListEmployee = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
		return new org.springframework.security.core.userdetails.User
			(user.getEmail(), user.getPassword(), user.getRoles().equals("ROLE_ADMIN") ? authorityListAdmin : authorityListEmployee);
	
		}
	}
	
//	@Override
//	public boolean equals(Object objUser) {
//		System.out.println((objUser instanceof User));
//		if(objUser == null) return false;
//		else if (!(objUser instanceof User)) return false;
//		else return (objUser.hashCode() == hashCode());
//	}
	
//	@Override
//	public int hashCode() {
//		return super.hashCode();
//	}
	

}