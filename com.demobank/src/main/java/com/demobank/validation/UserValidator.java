package com.demobank.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demobank.domain.User;
import com.demobank.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub

		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;

		long userId = user.getUserId();
//		System.out.println("User: " + user);
//		System.out.println("UserId: " + userId);
//		System.out.println("User exist: " + userService.findUserById(userId));
		
		try {
			if (userService.findUserById(userId).getUserEmail().equals(user.getUserEmail())) {
				errors.rejectValue("userEmail", "user.userEmail.exists", user.getUserEmail() + " is already existed !!!");
			}
			if (userService.findUserById(userId).getUsername().equals(user.getUsername())) {
				errors.rejectValue("username", "user.username.exists", user.getUsername() + " is already existed !!!");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}






















