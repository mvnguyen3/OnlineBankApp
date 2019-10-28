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
		if (userId < 0) {
			errors.rejectValue("userId", "user.userId.exists", "User Id must not be < 0");

		}
		try {
			if (!userService.findUserById(userId).equals(null)) {
				errors.rejectValue("userId", "user.userId.exists", userId + " is already existed !!!");
			}

			if (!userService.findUserById(userId).equals(null)) {
				errors.rejectValue("username", "user.username.exists", user.getUsername() + " is already existed");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
