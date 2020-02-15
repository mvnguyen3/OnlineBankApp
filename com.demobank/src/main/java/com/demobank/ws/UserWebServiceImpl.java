package com.demobank.ws;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.domain.User;
import com.demobank.repository.UserRepository;
import com.demobank.service.UserService;

@Service
public class UserWebServiceImpl implements UserWebService {

	@Autowired
	UserService service;
	
	
	
	@Override
	public UserResponse createUser(UserCreateRequest userCreateRequest) {
		System.out.println("userCreateRequest" + userCreateRequest);
		User user = new User();
		user.setUserId(service.getUserMaxId());
		user.setUsername(userCreateRequest.getUsername());
		user.setPassword(userCreateRequest.getPassword());
		user.setUserEmail(userCreateRequest.getUserEmail());
		user.setUserMobile(userCreateRequest.getUserMobile());
		
		User userFromRepo = service.saveUser(user);
		System.out.println("userCreateRequest-----User: " + userFromRepo);
		UserResponse res = new UserResponse();
		res.setMessage("CREATED");
		res.setMessage("Branches are listed below: ");
		List<User> users = service.findAllUsers();
		
		System.out.println("Users: " + users.toString());
		res.setUser(users);
		
		return res;
	}

	@Override
	public UserResponse getUserById(UserRequest userRequest) {
		User user = service.findUserById(userRequest.getUserId());
		List<User> users = new ArrayList<>();
		user.setPassword("****");
		users.add(user);
		UserResponse res = new UserResponse();
		res.setUser(users);
		res.setMessageCode("FOUND");
		res.setMessage("List of the users:");
		
		return res;
	}

	@Override
	public UserResponse getAllUsers() {
		List<User> users = service.findAllUsers();
		for(User user: users) {
			user.setPassword("****");
		}
		UserResponse res = new UserResponse();
		res.setUser(users);
		res.setMessageCode("FOUND");
		res.setMessage("List of the users:");
		return res;
	}

	@Override
	public UserResponse deleteUserById(UserRequest userRequest) {

		UserResponse res = new UserResponse();
		if(service.findUserById(userRequest.getUserId()) != null) {
			service.deleteUserById(userRequest.getUserId());
			System.out.println("userId: " +  userRequest.getUserId());
			res.setMessageCode("DELETED");
			res.setMessage("User with id "+ "\""+ userRequest.getUserId() + "\"" + " was deleted.");
			
		}
		else {
			res.setMessageCode("NOT FOUND");
			res.setMessage("No user with id "+ "\""+ userRequest.getUserId() + "\"" + " exists.");
		}
		
		return res;
	}

}

















