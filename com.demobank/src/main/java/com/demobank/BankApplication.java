package com.demobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);

		// Implement next
		// Done
		// + if customer doesn't registered yet, disable all the other features

		// Maintenance

		// Problem
		// When delete user, customer record didn't get delete but it works when delete
		// on customer page. // Solved

		// CREATION - Use Email to Link
		// + User Customer links -- Done
		// + Customer Account links
		// + 1 customer can have many Account. // TODO
//	 	+ When 1 account is created, set the customer object to that account.
		// + Use session to store customer email, and somehow get the customer object.
		// + Populate the customer_Account table also.

		// DELETION - TODO Use Id to link
		// + User Customer links -- Done
		// + Customer_account links -- TODO
		// + Once customer is got deleted - also delete the account. -- DONE TODO - If user is delete, Delete everything....
		// + 

		// once Account is created, link it with the current customer Id and the
		// BranchId

		// Once the user is deleted, every link should be deleted it also
		// Once the customer is deleted, Account, and Branch associate with that Id also
		// should be deleted.
		// Once the account is deleted, the branch associate with that account should be
		// also deleted.

		// 1 user can have 1 account.
		// 1 customer can have many account.

	}

}
