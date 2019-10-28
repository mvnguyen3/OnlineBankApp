package com.demobank.service;

import java.util.List;

import com.demobank.domain.Customer;

public interface CustomerService {
	List<Customer> findAll();
	void save(Customer customer);
	void deleteById(long id);
	Customer findById(long id);
	

}
