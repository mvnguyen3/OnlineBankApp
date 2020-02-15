package com.demobank.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demobank.domain.Customer;
import com.demobank.service.CustomerService;
import com.demobank.service.UserService;

@Component
public class CustomerValidator implements Validator {

	@Autowired
	CustomerService service;
	
	@Autowired
	UserService userService;
	

	@Override
	public boolean supports(Class<?> clazz) {

		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Customer customer = (Customer) target;

		long customerID = customer.getCustomerId();
		try {
			// Validate user input cannot be empty for all field	
			
			List<Customer> customers = service.findAllCustomer();			
			for(Customer c: customers) {
				if(customer.getCustomerEmail().equals(c.getCustomerEmail())) {
					errors.rejectValue("customerEmail", "customer.customerEmail.exists", c.getCustomerEmail() + " is already existed !!!");
				}
				if(customer.getCustomerPhone().equals(c.getCustomerPhone())){
					errors.rejectValue("customerPhone", "customer.customerPhone.exists", c.getCustomerPhone() + " is already existed !!!");
				}
				if(customer.getCustomerSsn().equals(c.getCustomerSsn())){
					errors.rejectValue("customerSsn", "customer.customerSsn.exists", c.getCustomerSsn() + " is already existed !!!");
				}
			}
			
			// If customer email doesn't match with user email  -- Will return null if no match
			if(userService.findUserByEmail(customer.getCustomerEmail()) == null) {
				errors.rejectValue("customerEmail", "customer.customerEmail.notMatch", customer.getCustomerEmail() + " does not match in record !!!");
			}
			if(service.findCustomerByEmail(customer.getCustomerEmail()) != null) {
				errors.rejectValue("customerEmail", "customer.customerEmail.Exist", customer.getCustomerEmail() + " is already exist !!!");
			}
			
			
		} catch (Exception e) {

		}

	}

}
















