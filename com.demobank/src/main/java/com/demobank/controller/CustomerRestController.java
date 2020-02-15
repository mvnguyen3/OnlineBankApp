package com.demobank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demobank.domain.Customer;
import com.demobank.service.CustomerService;

@RestController
@RequestMapping("/cusR")
public class CustomerRestController {
	
	@Autowired
	CustomerService service;
		
	@GetMapping("/showCustomer")
	ResponseEntity<?> show(){
		return new ResponseEntity<List<Customer>>(service.findAllCustomer(), HttpStatus.OK);
	}
	
	@PostMapping("/saveCustomer")
	ResponseEntity<?> save(@RequestBody Customer customer){
		if(service.findCustomerById(customer.getCustomerId()) == null) {
			System.out.println("Customer: " + customer);
			service.saveCustomer(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
		return new ResponseEntity<String>("User Existed", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("deleteCustomer")
	ResponseEntity<?> delete(@RequestBody long id) throws InterruptedException{
		if((service.findCustomerById(id)) != null) {			
			service.deleteCustomerById(id);
			return new ResponseEntity<String>("User with id: " + id + " has been deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("User with id: " + id + " is not existed", HttpStatus.BAD_REQUEST);
		
	}
}
