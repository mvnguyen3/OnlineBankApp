package com.demobank.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demobank.domain.Account;
import com.demobank.domain.Branch;
import com.demobank.domain.Customer;
import com.demobank.service.AccountService;
import com.demobank.service.BranchService;
import com.demobank.service.CustomerService;
import com.demobank.service.UserService;



@Controller
public class AccountController {
	
	@Autowired 
	AccountService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	BranchService branchService;
	
	@RequestMapping("/accountForm")
	ModelAndView customerForm(Account account, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("accountForm");
		modelAndView.addObject("accounts", service.findAllAccount());
		// ********** If user is not admin **********
		if (session.getAttribute("Admin") == null) {
		Customer customer = (Customer) session.getAttribute("customer");
		System.out.println("Current Customer: " + customer);
		modelAndView.addObject("holder", customer.getCustomerName());
		modelAndView.addObject("currentAccounts", service.findAllByCusId(customer.getCustomerId())); 
		
		}
		
		return modelAndView;
		
	}
	
	// Views include attributes
	private ModelAndView accountFormView(ModelAndView modelAndView, HttpSession session) {
		modelAndView.setViewName("accountForm");
		modelAndView.addObject("accounts", service.findAllAccount());
		if (session.getAttribute("Admin") == null) {
			Customer customer = (Customer) session.getAttribute("customer");
			System.out.println("Current Customer: " + customer);
			modelAndView.addObject("holder", customer.getCustomerName());
			modelAndView.addObject("currentAccounts", service.findAllByCusId(customer.getCustomerId())); 
			
			}

		return modelAndView;
	}
	
	@PostMapping("/saveAccount")
	ModelAndView saveForm(@ModelAttribute @Valid Account account, BindingResult br, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(service.findAllAccount());
		
		//For Validation
		if(br.hasErrors()) {
			System.out.println("Error");
			return accountFormView(modelAndView, session);
		}else {
			// *** Set customer to account ***
			//System.out.println("Current Customer: " + session.getAttribute("customer"));
			Branch branch = branchService.findById(1l);
			Customer customer = (Customer) session.getAttribute("customer");
			account.setAccountBranch(branch);
			account.setAccountCustomers(customer);
			service.saveAccount(account);
			// Save the link between account and customer; Doesn't work
		//	service.saveAccountCustomer(customer.getCustomerId(), account.getAccountID());
			//service.saveAccountCustomer(1L, 1L); // Testing
			System.out.println("Successfully save account and customer links");
			
			modelAndView.addObject("status", "Successfully save id: " + account.getAccountID());
			return accountFormView(modelAndView, session);
		}
		
	}
	
	// @ModelAttribute user is a need If you go back to the main page.
	@RequestMapping("/deleteAccount")
	ModelAndView delete(@ModelAttribute Account account, @RequestParam long accountId, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		service.deleteAccountById(accountId);
		modelAndView.addObject("status", "Customer with id: " + accountId + " has been deleted");

		return accountFormView(modelAndView, session);
	}
		
}




































