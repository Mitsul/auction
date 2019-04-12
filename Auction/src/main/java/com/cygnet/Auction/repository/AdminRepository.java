package com.cygnet.Auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.cygnet.Auction.model.Admin;

@Component
public interface AdminRepository extends JpaRepository<Admin, String> {

	Admin findByEmailAndPassword(String id, String password);

	Admin findByEmail(String email);

}
