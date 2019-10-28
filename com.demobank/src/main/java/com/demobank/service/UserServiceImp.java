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
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}


	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}


	@Override
	public void deleteById(long userId) {	
		userRepository.deleteByUserId(userId);
	}
	
	

	@Override
	public void updateUser() {
		// TODO Auto-generated method stub
		
	
	}

	public User findUserById(long userId) {			
		return userRepository.findByUserId(userId);
	}	
}


















