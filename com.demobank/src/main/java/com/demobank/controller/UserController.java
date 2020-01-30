package com.demobank.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
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

import com.demobank.domain.Account;
import com.demobank.domain.Branch;
import com.demobank.domain.Customer;
import com.demobank.domain.Transaction;
import com.demobank.domain.User;
import com.demobank.service.AccountService;
import com.demobank.service.BranchService;
import com.demobank.service.CustomerService;
import com.demobank.service.RoleService;
import com.demobank.service.TransactionService;
import com.demobank.service.UnifiedService;
import com.demobank.service.UserService;
import com.demobank.validation.UserValidator;
import com.demobank.config.SecurityConfig.*;

@Controller
public class UserController {

	@Autowired
	UnifiedService service;

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
		System.out.println("Hello ");
		// Initialize branch
		
		

		return model;
	}

	@RequestMapping("/userForm")
	ModelAndView userForm(User user, HttpSession session, Principal pl) {
		ModelAndView modelAndView = new ModelAndView("userForm");
		modelAndView.addObject("users", service.findAllUsers());

		try {
			if (service.findByUserName(pl.getName()) != null) {
				User currentUser = service.findByUserName(pl.getName());
				System.out.println("Current User: ***Admin***");
				session.setAttribute("user", currentUser);
				session.setAttribute("Admin", "In session");
				session.setAttribute("registered", "Not Null");
			}

		} catch (NullPointerException e) {
			System.out.println("New User");
		}

		try {

			user.setUserId(service.getUserMaxId());
		} catch (Exception e) {
			System.out.println("Sign Up User");
		}

		return modelAndView;
	}

	// ************ VIEW HAS OBJECT ***************
	// Set back the View Which contains all the Object
	ModelAndView userFormView(ModelAndView modelAndView) {
		modelAndView.setViewName("userForm");
		modelAndView.addObject("users", service.findAllUsers());

		return modelAndView;
	}
	// ************ VIEW HAS OBJECT ***************

	@PostMapping("/saveUserForm")
	ModelAndView saveForm(@ModelAttribute @Valid User user, BindingResult br, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(service.findAllUsers());
		// For Validation
		if (br.hasErrors()) {
			System.out.println("Error");
			return userFormView(modelAndView);
		} else {
			modelAndView.addObject("status", "Successfully save user");
			session.setAttribute("newUser", "Not Null");
			user.setPassword(pbkdf2.encode(user.getPassword()));			
			if(user.getUserId() > 0L) {
				service.saveLinkUserRole(user.getUserId(), 2L); // If the userId is more than 0. They are normal user.
				service.saveUser(user);
			}					
			else {
				service.saveLinkUserRole(user.getUserId(), 1L); 
				service.saveUser(user);
			}
			return userFormView(modelAndView);
		}

	}

	// @ModelAttribute user is a need If you go back to the main page.
	@RequestMapping("/deleteUser")
	ModelAndView deleteUser(@ModelAttribute User user, @RequestParam long userId, HttpSession session) throws InterruptedException {
		ModelAndView modelAndView = new ModelAndView();

		try {
			
			List<Customer> customers = service.findAllCustomer();
			for(Customer c: customers) {
				if(c.getUsers().getUserId() == userId) {
					System.out.println("Can't delete user \nThere is a customer's account which linked to this user");
					modelAndView.addObject("status", "User with id: " + userId + " still has a link to another customer");
					session.setAttribute("status", "failed");
				}
					
				else {
					session.setAttribute("status", "success");
					service.deleteUserById(userId);
					modelAndView.addObject("status", "User with id: " + userId + " has been deleted successfully");
				}
			}
			
			// ******************* OLD CODE ***********************************
//			// ALWAYS DELETE THE CHILD COMPONENT FIRST.
//			// Everything which is linked to this user is also be deleted
//			if (roleService.getCustomerId(userId) != 0) {
//				long customerLinkedId = roleService.getCustomerId(userId); // customer Id
//				List<Account> accountsOfCustomer = accountService.findAllByCusId(customerLinkedId);
//				boolean balanceExist = false, transactionExist = false;
//
//				for (Account acc : accountsOfCustomer) {
//					if (acc.getAccountBalance() > 0) {
//						System.out
//								.println(acc.getAccountHolder() + " Has Balance Available: " + acc.getAccountBalance());
//						balanceExist = true;
//					}
//				}
//
//				// Find transaction from each account;
//				for (Account acc : accountsOfCustomer) {
//					if (!tranService.findByFromAccNumber(acc.getAccountID()).isEmpty()) {
//						System.out.println(acc.getAccountHolder() + " Has transaction Available: "
//								+ tranService.findByFromAccNumber(acc.getAccountID()));
//						transactionExist = true;
//					}
//				}
//
//				// If there is no balance and transaction on the account. We can delete the
//				// user.
//				if (!balanceExist && !transactionExist) {
//					accountService.deleteAccountByCusId(customerLinkedId); // Delete accounts that associate with the
//																			// customer.
//					// System.out.println("CustomerLinkedId: " + customerLinkedId);
//					// Delete customer linked
//					customerService.deleteById(customerLinkedId); // Customer Link Delete
//
//					Thread.sleep(500);
//					userService.deleteById(userId);
//					modelAndView.addObject("status", "User with id: " + userId + " has been deleted");
//				} else {
//					modelAndView.addObject("status",
//							"User with id: " + userId + " still has some balance and transactions");
//				}
//				// If there is no link customer...
//			} else {
//				/*
//				 * userService.deleteById(userId); modelAndView.addObject("status",
//				 * "User with id: " + userId + " has been deleted");
//				 */
//			}
			// ******************* OLD CODE ***********************************

		} catch (Exception e) {
			System.out.println("Null Pointer Exception");
		}

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
		service.deleteUserById(userId);

		// Finally, modify the temp user and save it back to the database
		tempUser.setUserEmail(userEmail);
		tempUser.setUserMobile(userMobile);

		service.saveUser(tempUser);
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
		
		// ************** Initializing *************
		try {
			if (service.findAllBranch().isEmpty()) {
				System.out.println("Initializing branch...");
				service.saveBranch(new Branch("US", "Il", "Chicago", "60630"));

			}
			// *********

			// Initialize Role
				if(service.findRole().isEmpty()) {				
					System.out.println("Initializing role...");
					service.saveRole(1L, "admin");
					service.saveRole(2L, "user");
					
				}		
			// **************
				
				// Initialize user
//				if(userService.findUserById(0L) == null) {
//					System.out.println("Initializing User");
//					userService.saveUser(new User(0L, "minh", "minh"));
//				}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		// ***********************************
		
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
			// System.out.println("*****auth: " + auth);
			// System.out.println("*****principal: " +
			// SecurityContextHolder.getContext().getAuthentication());

			// Once logout, session will automatically invalid

			errorMessage = "You have been logged out sucessfully";
		}

		model.addAttribute("errorMessage", errorMessage);
		return modelAndView;
	}

	// **********************************

}
