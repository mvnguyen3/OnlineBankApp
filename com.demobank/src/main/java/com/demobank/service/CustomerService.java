package com.demobank.service;

import java.util.List;

import com.demobank.domain.Customer;

public interface CustomerService {
	List<Customer> findAllCustomer();

	void saveCustomer(Customer customer);

	void deleteCustomerById(long id) throws InterruptedException;

	Customer findCustomerById(long id);

	void saveLinkCustomerUser(long customerId, long userId);

	Customer findCustomerByEmail(String email);

	Customer findCustomerByName(String name);

}
