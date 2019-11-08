package com.demobank.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Account {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountID;	
	//private AccountType accountType;
	private String accountType;
	private String accountHolder;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private String accountOpenDate;
	
	// One Branch can have many accounts
	@ManyToOne
	@JoinColumn(name = "branchId") // Link to branchId
	@JsonBackReference
	private Branch accountBranch;
	
	private double accountBalance;
	
	@ManyToOne
	@JoinColumn(name = "customerId") // Link to customerId on customer table
	private Customer accountCustomers;
	

	// Set does not allow duplicate transactions
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="account_transaction", 
	joinColumns = {@JoinColumn(name = "accountID")}, 
	inverseJoinColumns = {@JoinColumn(name = "transactionId")}
	)
	private Set<Transaction> accountTransactions = new HashSet<>(); 
	
	// Default Constructor
	public Account() {
		
	}
	
	
	// Getter, Setter

	public long getAccountID() {
		return accountID;
	}

	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getAccountOpenDate() {
		return accountOpenDate;
	}

	public void setAccountOpenDate(String accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}

	public Branch getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(Branch accountBranch) {
		this.accountBranch = accountBranch;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Customer getAccountCustomers() {
		return accountCustomers;
	}

	public void setAccountCustomers(Customer accountCustomers) {
		this.accountCustomers = accountCustomers;
	}

	public Set<Transaction> getAccountTransactions() {
		return accountTransactions;
	}

	public void setAccountTransactions(Set<Transaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}


	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountType=" + accountType + ", accountHolder=" + accountHolder
				+ ", accountOpenDate=" + accountOpenDate + ", accountBranch=" + accountBranch + ", accountBalance="
				+ accountBalance + ", accountCustomers=" + accountCustomers + ", accountTransactions="
				+ accountTransactions + "]";
	}
}















































