package com.demobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);	
		
		
		// 
		
		// Implement next
		// TODO 
		// + if customer doesn't registered yet, disable all the other features
		
		
		
		// CREATION - TODO Use Email to Link
		// + User Customer links -- Done
		// + Customer Account links -- TODO
		//		+ 1 customer can have many Account.
		
		
		
		
		
		// DELETION - TODO Use Id to link
		// + User Customer links -- Done
		//
		
		
		// once Account is created, link it with the current customer Id and the BranchId
		
		
		
		
		
		// Once the user is deleted, every link should be deleted it also
		// Once the customer is deleted, Account, and Branch associate with that Id also should be deleted.
		// Once the account is deleted, the branch associate with that account should be also deleted.
		
		// 1 user can have 1 account.
		// 1 customer can have many account.
		
		
	}

	
}
  	





 