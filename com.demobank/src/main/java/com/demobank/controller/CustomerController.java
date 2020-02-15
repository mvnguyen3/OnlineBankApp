package com.demobank.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
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

import com.demobank.domain.Account;
import com.demobank.domain.Customer;
import com.demobank.domain.User;
import com.demobank.service.AccountService;
import com.demobank.service.CustomerService;
import com.demobank.service.TransactionService;
import com.demobank.service.UserService;
import com.demobank.validation.CustomerValidator;

@Controller
public class CustomerController {

	@Autowired
	CustomerService service;

	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionService tranService;

	@Autowired
	CustomerValidator customervalidator;

	@InitBinder
	void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(customervalidator);
	}

	@RequestMapping("/customerForm")
	ModelAndView customerForm(Customer customer, HttpSession session, Principal pl) {
		ModelAndView modelAndView = new ModelAndView("customerForm");
		modelAndView.addObject("customers", service.findAllCustomer());
		// System.out.println("Customer: " + service.findAll());

		// ********** If user is not admin **********
		if (session.getAttribute("Admin") == null) {
			System.out.println("Name from Pricipal: " + pl.getName());

			// Check if customer is already registered
			try {
				// check by using useremail.
				User user = userService.findByUserName(pl.getName());
				String userEmail = user.getUserEmail();
				// System.out.println("User Email:" + userEmail);
				Customer currentCustomer = service.findCustomerByEmail(userEmail);

				session.setAttribute("email", user.getUserEmail());
				session.setAttribute("customer", currentCustomer);
				System.out.println("customer object has been store to the session :)");

				System.out.println("From Customer Controller*** Current Customer: " + currentCustomer);

				session.setAttribute("registered", currentCustomer.getCustomerEmail());

			} catch (NullPointerException e) {
				e.printStackTrace();
				session.setAttribute("registered", null);
				System.out.println("SetAttribute registered to null");

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
		modelAndView.addObject("customers", service.findAllCustomer());
		return modelAndView;
	}

	@PostMapping("/saveCustomer")
	ModelAndView saveForm(@ModelAttribute @Valid Customer customer, BindingResult br) {
		ModelAndView modelAndView = new ModelAndView();
		// System.out.println(service.findAll());

		// For Validation
		if (br.hasErrors()) {
			System.out.println("Error");
			return customerFormView(modelAndView);
		} else {
			// Link the user to the customer
			customer.setUsers(userService.findUserByEmail(customer.getCustomerEmail()));
			service.saveCustomer(customer);

			// Get user Object using customer email
			// CustomerEmail will always be valid at this point!!
			User user = userService.findUserByEmail(customer.getCustomerEmail());

			// Link with existing userId
			// System.out.println("Customer ID: " + customer.getCustomerId());
			// service.saveCustomerUser(customer.getCustomerId(), user.getUserId());

			modelAndView.addObject("status", "Successfully save id: " + customer.getCustomerId());
			System.out.println("Successfully save user: " + customer.getCustomerName());
			return customerFormView(modelAndView);
		}

	}

	// @ModelAttribute user is a need If you go back to the main page.
	@RequestMapping("/deleteCustomer")
	ModelAndView delete(@ModelAttribute Customer customer, @RequestParam long customerId, HttpSession session) throws InterruptedException {
		ModelAndView modelAndView = new ModelAndView();

		List<Account> accountsOfCustomer = accountService.findAllAccountByCusId(customerId);
		boolean balanceExist = false, transactionExist = false;

		// Find all balance from account
		for (Account acc : accountsOfCustomer) {
			if (acc.getAccountBalance() > 0) {
				System.out.println(acc.getAccountHolder() + " Has Balance Available: " + acc.getAccountBalance());
				balanceExist = true;
			}
		}

		// Find transaction from each account;
		for (Account acc : accountsOfCustomer) {
			if (!tranService.findTransactionByFromAccNumber(acc.getAccountID()).isEmpty()) {
				System.out.println(acc.getAccountHolder() + " Has transaction Available: "
						+ tranService.findTransactionByFromAccNumber(acc.getAccountID()));
				transactionExist = true;
			}
		}

		if (!balanceExist && !transactionExist) {
			// Delete accounts that associate with the customer.
			accountService.deleteAccountByCusId(customerId);
			service.deleteCustomerById(customerId);
			session.setAttribute("status", "success");

			modelAndView.addObject("status", "Customer with id: " + customerId + " has been deleted");
		} else {
			modelAndView.addObject("status",
					"Customer with id: " + customerId + " still have some balances and transactions");
			session.setAttribute("status", "failed");
		}

		return customerFormView(modelAndView);
	}

	@RequestMapping("/updateCustomer")
	ModelAndView update(@RequestParam long customerId, @RequestParam String customerName,
			@RequestParam String customerEmail, @RequestParam String customerPhone, @RequestParam String customerGender,
			@RequestParam String customerSsn, @RequestParam String customerDob) {
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
	ModelAndView updatexxx(@ModelAttribute Customer customer, @RequestParam long customerId,
			@RequestParam String customerName, @RequestParam String customerEmail, @RequestParam String customerPhone,
			@RequestParam String customerGender, @RequestParam String customerSsn, @RequestParam String customerDob)
			throws InterruptedException {
		ModelAndView modelAndView = new ModelAndView();

		// First, save the current user into the user temp object
		System.out.println("Original Customer: " + customer);
		Customer tempUser = customer;
		System.out.println("customerId: " + customerId);
		// Then delete the selected user on the database
		service.deleteCustomerById(customerId);

		// ******** MODIFY FIELD *********
		tempUser.setCustomerName(customerName);
		tempUser.setCustomerEmail(customerEmail);
		tempUser.setCustomerPhone(customerPhone);

		service.saveCustomer(tempUser);
		System.out.println("Saving customer: " + tempUser);
		modelAndView.addObject("status", "Updated user with id: " + customerId);

		return customerFormView(modelAndView);
	}

}
