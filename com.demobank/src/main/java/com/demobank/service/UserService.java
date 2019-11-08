package com.demobank.service;

import java.util.List;

import com.demobank.domain.User;

public interface UserService {
	User saveUser(User user);
	List<User> findAll();
	void deleteById(long userId);
	void updateUser();
	User findUserById(long userId);
	long getMaxId();
	User findUserByEmail(String email);
	User findByUserName(String username);
}
