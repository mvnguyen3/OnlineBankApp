package com.demobank.service;

import java.util.List;

import com.demobank.domain.User;

public interface UserService {
	User saveUser(User user);
	List<User> findAllUsers();
	void deleteUserById(long userId);
	void updateUser();
	User findUserById(long userId);
	long getUserMaxId();
	User findUserByEmail(String email);
	User findByUserName(String username);
}
