package com.demobank.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.demobank.domain.User;
import com.demobank.repository.UserRepository;

@Service
@Transactional
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}


	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}


	@Override
	public void deleteById(long userId) {	
		System.out.println("Deleted User Id: " + userId);
		userRepository.deleteById(userId);
	}
	
	

	@Override
	public void updateUser() {
		
		
	
	}

	public User findUserById(long userId) {			
		return userRepository.findByUserId(userId);
	}


	@Override
	public long getMaxId() {
		
		return userRepository.getMaxId() + 1L;
	}


	@Override
	public User findUserByEmail(String email) {
		try {
			return userRepository.findUserByEmail(email);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}


	@Override
	public User findByUserName(String username) {
		
	//	System.out.println("User: ***" + userRepository.findByUsername(username));
		return userRepository.findByUsername(username);
	}	
}


















