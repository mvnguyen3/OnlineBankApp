package com.demobank.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.swing.text.DefaultEditorKit.CutAction;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demobank.domain.Customer;
import com.demobank.domain.User;
import com.demobank.service.CustomerService;
import com.demobank.service.UserService;
import com.demobank.validation.CustomerValidator;


@Controller
public class CustomerController {
	
	@Autowired 
	CustomerService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerValidator customervalidator;
	
	@InitBinder
	void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(customervalidator);
	}
	@RequestMapping("/customerForm")
	ModelAndView customerForm(Customer customer, HttpSession session, Principal pl) {
		ModelAndView modelAndView = new ModelAndView("customerForm");
		modelAndView.addObject("customers", service.findAll());
		
		// ********** If user is not admin **********
		if(session.getAttribute("Admin") == null) {
			System.out.println("Name from Pricipal: " + pl.getName());
			
			// Check if customer is already registered
			try {
				// check by using useremail.
				User user = userService.findByUserName(pl.getName());
				String userEmail = user.getUserEmail();
				System.out.println("User Email:" + userEmail);
				Customer currentCustomer = service.findByEmail(userEmail);
				if(currentCustomer == null) {
					System.out.println("Current Customer: " + currentCustomer);
					System.out.println("User is already registered an account");
					System.out.println("Customer Email: " + currentCustomer.getCustomerEmail());
					
				}
				session.setAttribute("registered", currentCustomer.getCustomerEmail());
				session.setAttribute("user", currentCustomer);
				
				
			}catch(NullPointerException e) {
				System.out.println("No Account With Email Provided");
				User user = userService.findByUserName(pl.getName());
				session.setAttribute("email", user.getUserEmail());
			}
		}
		// ************************************************************
		
		
		
//		if (session.getAttribute("inSession") == null && (session.getAttribute("Admin") == null)) {
//			System.out.println("*****My customer Name: " + customer.getCustomerName());
//			System.out.println("Current Customer Login: " + service.findByName(customer.getCustomerName()));
//			session.setAttribute("user", service.findByName(customer.getCustomerName()));
//			session.setAttribute("inSession", "Not Null");
//		}
		// Get current user object here
		
		
		
		return modelAndView;
		
	}
	// Views include attributes
	private ModelAndView customerFormView(ModelAndView modelAndView) {
		modelAndView.setViewName("customerForm");
		modelAndView.addObject("customers", service.findAll());
		return modelAndView;
	}
	
	@PostMapping("/saveCustomer")
	ModelAndView saveForm(@ModelAttribute @Valid Customer customer, BindingResult br) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(service.findAll());
		
		//For Validation
		if(br.hasErrors()) {
			System.out.println("Error");
			return customerFormView(modelAndView);
		}else {
			service.save(customer);

			// Get user Object using customer email
			// CustomerEmail will always be valid at this point!!
			User user = userService.findUserByEmail(customer.getCustomerEmail());
			
			
			// Link with existing userId
			System.out.println("Customer ID: " + customer.getCustomerId());
			service.saveCustomerUser(customer.getCustomerId(), user.getUserId());
			
			modelAndView.addObject("status", "Successfully save id: " + customer.getCustomerId());
			return customerFormView(modelAndView);
		}
		
	}
	
	// @ModelAttribute user is a need If you go back to the main page.
	@RequestMapping("/deleteCustomer")
	ModelAndView delete(@ModelAttribute Customer customer, @RequestParam long customerId) {
		ModelAndView modelAndView = new ModelAndView();
		service.deleteById(customerId);
		// Delete the links between customer and user.
		
		
		modelAndView.addObject("status", "Customer with id: " + customerId + " has been deleted");

		return customerFormView(modelAndView);
	}
	
	
	@RequestMapping("/updateCustomer")
	ModelAndView update(@RequestParam long customerId, @RequestParam String customerName,
			@RequestParam String customerEmail, @RequestParam String customerPhone, 
			@RequestParam String customerGender, @RequestParam String customerSsn,
			@RequestParam String customerDob) 
	{
		ModelAndView modelAndView = new ModelAndView("updateCustomerForm");
		Map<String, String> modelMap = new HashMap<>();
		modelMap.put("customerName", customerName);
		modelMap.put("customerEmail", customerEmail);
		modelMap.put("customerPhone", customerPhone);
		modelMap.put("customerGender", customerGender);
		modelMap.put("customerSsn", customerSsn);
		modelMap.put("customerDob", customerDob);
		
		modelAndView.addAllObjects(modelMap);
		modelAndView.addObject("customerId", customerId);
		return modelAndView;
		
	}
	
	@GetMapping("/updateCustomerForm2")
	ModelAndView updatexxx(@ModelAttribute Customer customer, @RequestParam long customerId, @RequestParam String customerName,
			@RequestParam String customerEmail, @RequestParam String customerPhone, 
			@RequestParam String customerGender, @RequestParam String customerSsn,
			@RequestParam String customerDob) {
		ModelAndView modelAndView = new ModelAndView();

		// First, save the current user into the user temp object
		System.out.println("Original Customer: " + customer);
		Customer tempUser = customer;
		System.out.println("customerId: " + customerId);
		// Then delete the selected user on the database
		service.deleteById(customerId);

		// ******** MODIFY FIELD *********
		tempUser.setCustomerName(customerName);
		tempUser.setCustomerEmail(customerEmail);
		tempUser.setCustomerPhone(customerPhone);
				
		service.save(tempUser);
		System.out.println("Saving customer: " + tempUser);
		modelAndView.addObject("status", "Updated user with id: " + customerId);
		

		return customerFormView(modelAndView);
	}

	
}




































