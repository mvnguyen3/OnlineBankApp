package com.demobank.controller;

import java.util.List;

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
import com.demobank.domain.Transaction;
import com.demobank.service.AccountService;
import com.demobank.service.BranchService;
import com.demobank.service.CustomerService;
import com.demobank.service.TransactionService;
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

	@Autowired
	TransactionService transervice;

	@RequestMapping("/accountForm")
	ModelAndView customerForm(Account account, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("accountForm");
		modelAndView.addObject("accounts", service.findAllAccount());
		// ********** If user is not admin **********
		if (session.getAttribute("Admin") == null) {
			// Don't do anything if the customer is not yet registered
			if(session.getAttribute("registered") == null)
				return modelAndView;
			Customer customer = (Customer) session.getAttribute("customer");
			System.out.println("Current Customer: " + customer);
			modelAndView.addObject("holder", customer.getCustomerName());
			modelAndView.addObject("currentAccounts", service.findAllAccountByCusId(customer.getCustomerId()));

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
			modelAndView.addObject("currentAccounts", service.findAllAccountByCusId(customer.getCustomerId()));

		}

		return modelAndView;
	}

	@PostMapping("/saveAccount")
	ModelAndView saveForm(@ModelAttribute @Valid Account account, BindingResult br, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(service.findAllAccount());

		// For Validation
		if (br.hasErrors()) {
			System.out.println("Error");
			return accountFormView(modelAndView, session);
		} else {
			// *** Set customer to account ***
			// System.out.println("Current Customer: " + session.getAttribute("customer"));
			Branch branch = branchService.findBranchById(1l);
			Customer customer = (Customer) session.getAttribute("customer");
			account.setAccountBranch(branch);
			account.setAccountCustomers(customer);
			service.saveAccount(account);
			// Save the link between account and customer; Doesn't work
			// service.saveAccountCustomer(customer.getCustomerId(),
			// account.getAccountID());
			// service.saveAccountCustomer(1L, 1L); // Testing
			System.out.println("Successfully save account and customer links");

			modelAndView.addObject("status", "Successfully save id: " + account.getAccountID());
			return accountFormView(modelAndView, session);
		}

	}

	// @ModelAttribute user is a need If you go back to the main page.
	@RequestMapping("/deleteAccount")
	ModelAndView delete(@ModelAttribute Account account, @RequestParam long accountId, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();

		// Validation goes here...
		// find all transaction by using accountId.
		List<Transaction> transactions = transervice.findTransactionByFromAccNumber(accountId);
		
		if (transactions.isEmpty()) {
			service.deleteAccountById(accountId);
			modelAndView.addObject("status", "Account with id: " + accountId + " has been deleted");
			session.setAttribute("status", "success");
			
		}else {
			System.out.println("Transactions from input account: " + transactions);
			System.out.println("Can't delete account with transaction available!!");
			session.setAttribute("status", "failed");
			modelAndView.addObject("status", "Account with id: " + accountId + " still have some transactions on it");
		}
		

		return accountFormView(modelAndView, session);
	}

}
