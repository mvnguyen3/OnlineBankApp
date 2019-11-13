package com.demobank.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "transaction")
public class Transaction {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment next id by sql
	private long transactionId;

	private long fromAccountNumber;

	private long toAccountNumber;

	private String comment;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date transactionDateTime;

	private String transactionType;

	@ManyToMany(mappedBy = "accountTransactions", fetch = FetchType.LAZY)
	private Set<Account> transactionAccounts = new HashSet<>();

	// Default Constructor
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	// Getter and Setter
	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(long fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public long getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(long toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Set<Account> getTransactionAccounts() {
		return transactionAccounts;
	}

	public void setTransactionAccounts(Set<Account> transactionAccounts) {
		this.transactionAccounts = transactionAccounts;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", fromAccountNumber=" + fromAccountNumber
				+ ", toAccountNumber=" + toAccountNumber + ", comment=" + comment + ", transactionDateTime="
				+ transactionDateTime + ", transactionType=" + transactionType + ", transactionAccounts="
				+ transactionAccounts + "]";
	}
}
