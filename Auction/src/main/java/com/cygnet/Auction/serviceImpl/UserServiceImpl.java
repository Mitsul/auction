package com.cygnet.Auction.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cygnet.Auction.model.User;
import com.cygnet.Auction.repository.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepository; 
	
//		public List<User> users = new ArrayList<>(
//			 Arrays.asList(
//						new User("123","mitsul","abc","abc city"),
//						new User("456","Preet","xyz","xyz city")
//					));
	
	public List<User> getAllUser(){
		//return users;
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users :: add);
		return users;
	}

	public Optional<User> getUser(String id) {
		//return users.stream().filter(u->u.getId().equals(id)).findFirst().get();
		return userRepository.findById(id);
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}

	public void updateUser(String id, User user) {
//		for(int i =0 ; i < users.size() ; i++) {
//			User u = users.get(i);
//			if(u.getId().equals(id)) {
//				users.set(i, user);
//			}
//		}
		userRepository.save(user);
	}

	public void deleteUser(String id) {
		//users.removeIf(u->u.getId().equals(id));
		userRepository.deleteById(id);
	}

}
