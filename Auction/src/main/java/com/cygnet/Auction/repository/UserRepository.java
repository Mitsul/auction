package com.cygnet.Auction.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.User;



@Component
public interface UserRepository extends JpaRepository<User, String>{

	
	
}
