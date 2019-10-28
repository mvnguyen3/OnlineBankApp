package com.demobank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.domain.Customer;
import com.demobank.repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	CustomerRepository repository;
	
	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		repository.save(customer);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Customer findById(long id) {
		// TODO Auto-generated method stub
		try {
			return repository.findById(id).get(); // User might or might not exist)
		}catch(Exception e) {
			return null;
		}
		
	}

}
