package com.demobank.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.domain.Customer;
import com.demobank.repository.CustomerRepository;

@Service
@Transactional
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
		repository.deleteUserLink(id);
		repository.deleteById(id);
		if(findAll().isEmpty()) {
			repository.truncate();
		}
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

	@Override
	public void saveCustomerUser(long customerId, long userId) {
		repository.saveCustomerUser(customerId, userId);
		
	}

	@Override
	public Customer findByEmail(String email) {
	
		return repository.findByEmail(email);
	}

	@Override
	public Customer findByName(String name) {
		System.out.println("Name: " + repository.findByName(name));
		return repository.findByName(name);
	}

}



















