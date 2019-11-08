package com.demobank.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.demobank.domain.Customer;
import com.demobank.domain.User;
import com.demobank.service.CustomerService;
import com.demobank.service.RoleService;
import com.demobank.service.UserService;
import com.demobank.validation.UserValidator;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	// Use to save userId with UserRole
	@Autowired
	RoleService roleService;

	@Autowired
	CustomerService customerService;

	@Autowired
	UserValidator userValidator;

	@Autowired
	Pbkdf2PasswordEncoder pbkdf2;

	// Validators
	@InitBinder
	void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(userValidator);

	}

	// Main entrance of the form
	@RequestMapping("/")
	ModelAndView welcome() {
		ModelAndView model = new ModelAndView("welcome");

		return model;
	}

	@RequestMapping("/userForm")
	ModelAndView userForm(User user, HttpSession session, Principal pl) {
		ModelAndView modelAndView = new ModelAndView("userForm");
		modelAndView.addObject("users", userService.findAll());
		User currentUser = userService.findByUserName(pl.getName());
		
		// ************ TESTING AREA **************
//		System.out.println("Name from Pricipal: " + pl.getName());
//		System.out.println("UserId: " + currentUser.getUserId() + " Link with CustomerId: "
//				+ roleService.getCustomerId(2L));
		
		// ***************************************
		
		session.setAttribute("user", currentUser);
		session.setAttribute("Admin", "In session");

//		if (session.getAttribute("inSession") == null) {
//			System.out.println("*****My user Name: " + user.getUsername());
//			System.out.println("Current User Login: " + userService.findByUserName(user.getUsername()));
//			session.setAttribute("user", userService.findByUserName(user.getUsername()));
//			session.setAttribute("Admin", "In session");
//			// session.setAttribute("sessionInit", "Yes");
//			session.setAttribute("inSession", "Not Null");
//
//		}

		try {
			user.setUserId(userService.getMaxId());
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			modelAndView.addObject("status", "Successfully save user");

			user.setPassword(pbkdf2.encode(user.getPassword()));
			roleService.saveRole(user.getUserId(), 2L);
			userService.saveUser(user);

			return userFormView(modelAndView);
		}

	}

	// @ModelAttribute user is a need If you go back to the main page.
	@RequestMapping("/deleteUser")
	ModelAndView deleteUser(@ModelAttribute User user, @RequestParam long userId) {
		ModelAndView modelAndView = new ModelAndView();
		
		// Everything which is linked to this user is also be deleted
		userService.deleteById(userId); 
		long customerLinkedId = roleService.getCustomerId(userId);
		customerService.deleteById(customerLinkedId); // Customer Link Delete
		
		
		
		modelAndView.addObject("status", "User with id: " + userId + " has been deleted");

		return userFormView(modelAndView);
	}

	// ************* UPDATE **************
	// For update Button
	// This method will redirect to updateUserForm.jsp
	// Delete the userById then save the user again
	@RequestMapping("/updateUser")
	ModelAndView updateUserJsp(@RequestParam long userId, @RequestParam String username, @RequestParam String password,
			@RequestParam String userEmail, @RequestParam String userMobile) {
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

	// ************* LOGIN LOGOUT**************
	// Cannot get credential from here...
	int count = 0;

	@GetMapping("login")
	public ModelAndView logToApp(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest req,
			HttpServletResponse res, Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("login");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String errorMessage = null;
		if (error != null) {
			count++;
			errorMessage = "Either username or password is incorrect.";
			if (count >= 3) {
				errorMessage = "Please sign up with our webpage";
			}

		}
		if (logout != null) {

			if (auth != null) {
				new SecurityContextLogoutHandler().logout(req, res, auth);
			}
			System.out.println("*****auth: " + auth);
			System.out.println("*****principal: " + SecurityContextHolder.getContext().getAuthentication());

			// Once logout, session will automatically invalid

			errorMessage = "You have been logged out sucessfully";
		}

		model.addAttribute("errorMessage", errorMessage);
		return modelAndView;
	}

	// **********************************

}
