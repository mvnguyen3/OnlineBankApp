package com.demobank.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.demobank.domain.User;
import com.demobank.service.UserService;
import com.demobank.validation.UserValidator;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserValidator userValidator;

	// Validators
	@InitBinder
	void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(userValidator);

	}

	// Main entrance of the form
	@RequestMapping("/")
	ModelAndView userForm(User user) {
		ModelAndView modelAndView = new ModelAndView("userForm");
		modelAndView.addObject("users", userService.findAll());
		return modelAndView;
	}

	// ************ VIEW HAS OBJECT ***************
	// Set back the View Which contains all the Object
	ModelAndView userFormView(ModelAndView modelAndView) {
		modelAndView.setViewName("userForm");
		modelAndView.addObject("users", userService.findAll());
		return modelAndView;
	}
	// ************ VIEW HAS OBJECT ***************

	@PostMapping("/saveUserForm")
	ModelAndView saveForm(@ModelAttribute @Valid User user, BindingResult br) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(userService.findAll());
		// For Validation
		if (br.hasErrors()) {
			System.out.println("Error");
			return userFormView(modelAndView);
		} else {
			userService.saveUser(user);
			return userFormView(modelAndView);
		}

	}

	// @ModelAttribute user is a need If you go back to the main page.
	@RequestMapping("/deleteUser")
	ModelAndView deleteUser(@ModelAttribute User user, @RequestParam long userId) {
		ModelAndView modelAndView = new ModelAndView();
		userService.deleteById(userId);
		modelAndView.addObject("status", "User with id: " + userId + " has been deleted");

		return userFormView(modelAndView);
	}

	// ************* UPDATE **************
	// For update Button
	// This method will redirect to updateUserForm.jsp
	// Delete the userById then save the user again
	@RequestMapping("/updateUser")
	ModelAndView updateUserJsp(@RequestParam long userId, @RequestParam String username,
			@RequestParam String password,
			@RequestParam String userEmail,
			@RequestParam String userMobile) {
		ModelAndView modelAndView = new ModelAndView("updateUserForm");
		Map<String, String> modelMap = new HashMap<String, String>();
		modelMap.put("username", username);
		modelMap.put("password", password);
		modelMap.put("userEmail", userEmail);
		modelMap.put("userMobile", userMobile);
		modelAndView.addAllObjects(modelMap);
		modelAndView.addObject("userId", userId);
		

		return modelAndView;
	}

	@GetMapping("/updateUserForm2")
	ModelAndView updatexxx(@ModelAttribute User user, @RequestParam long userId, @RequestParam String userEmail,
			@RequestParam String userMobile) {
		ModelAndView modelAndView = new ModelAndView();

		// First, save the current user into the user temp object
		System.out.println("Original User: " + user);
		User tempUser = user;
		System.out.println("userId: " + userId);
		// Then delete the selected user on the database
		userService.deleteById(userId);

		// Finally, modify the temp user and save it back to the database
		tempUser.setUserEmail(userEmail);
		tempUser.setUserMobile(userMobile);
		
		userService.saveUser(tempUser);
		System.out.println("Saving user: " + tempUser);
		modelAndView.addObject("status", "Updated user with id: " + userId);
		

		return userFormView(modelAndView);
	}

	// ************* UPDATE **************

}
