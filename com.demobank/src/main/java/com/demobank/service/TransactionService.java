package com.demobank.service;

import java.util.List;

import com.demobank.domain.Transaction;

public interface TransactionService {
	void save(Transaction transaction); // Assumed transaction object is always be valid
	List<Transaction> findAll();
	void deleteByIdfix(long ToAccountNumber);
}
