package com.cygnet.Auction.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cygnet.Auction.model.User;
import com.cygnet.Auction.serviceImpl.UserServiceImpl;



@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping("/user")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}
	
	@RequestMapping("/user/{id}")
	public Optional<User> getUser(@PathVariable String id) {
		return userService.getUser(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/user")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/user/{id}")
	public void updateUser(@PathVariable String id, @RequestBody User user) {
		userService.updateUser(id, user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/user/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
	}
}
