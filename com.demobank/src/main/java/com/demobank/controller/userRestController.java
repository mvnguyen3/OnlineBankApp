package com.demobank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demobank.domain.User;
import com.demobank.service.UnifiedService;
import com.demobank.service.UserService;

@RestController
public class userRestController {
	@Autowired
	UnifiedService service;
	
	@PostMapping("/saveUser")
	ResponseEntity<?> saveUser(@RequestBody User user){
		if(service.findUserById(user.getUserId()) == null) {
			service.saveUser(user);
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("User existed", HttpStatus.BAD_REQUEST);		
	}
	
	
	@GetMapping("/showUser")
	ResponseEntity<?> showUser(){
		return new ResponseEntity<List<User>>(service.findAllUsers(), HttpStatus.OK);
	}
	
	@PostMapping("/deleteUserById")
	ResponseEntity<?> deleteUser(@RequestBody long id){
		if(service.findUserById(id) != null) {
			service.deleteUserById(id);
			return new ResponseEntity<String>("User with id:" + id + " has been deleted!!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("User with id: " + id + " does not existed", HttpStatus.BAD_REQUEST);
		
	}
	
}
