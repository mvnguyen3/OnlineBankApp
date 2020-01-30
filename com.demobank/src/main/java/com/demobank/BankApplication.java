package com.demobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BankApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
		
		
		//TODO
		/*
		 * When deleting a user, must check if customer is there.
		 * Apache Tomcat 8.5.50 	
		 * */
		 
		// To host to Tomcat 8.5
		// + Remove embedded Tomcat in spring
		// + extends SpringBootServletInitializer..
		
		// Implement next
		// Done
		// + if customer doesn't registered yet, disable all the other features

		// Maintenance

		// Problem
		// When delete user, customer record didn't get delete but it works when delete
		// on customer page. // Solved
		//awdawd
		

		// CREATION - Use Email to Link
		// + User Customer links -- Done
		// + Customer Account links
		// + 1 customer can have many Account. // done
//	 	+ When 1 account is created, set the customer object to that account.
		// + Use session to store customer email, and somehow get the customer object.
		// + Populate the customer_Account table also. 

		// DELETION - Use Id to link
		// + User Customer links -- Done
		// + Customer_account links -- Done
		// + If user is delete, Delete everything except the branch
		// + Once customer is got deleted - also delete the account. -- DONE - 
		// 
		// + Creating transaction component -- Done
		// 		+ Prevent deletion from user, customer, account, if there is some transaction between account and some account balance... -DONE

		// once Account is created, link it with the current customer Id and the
		// BranchId



	}
	
}
