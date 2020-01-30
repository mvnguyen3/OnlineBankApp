package com.demobank.service;

import java.util.List;

import com.demobank.domain.Transaction;

public interface TransactionService {
	void saveTransaction(Transaction transaction); // Assumed transaction object is always be valid
	List<Transaction> findAllTransactions();
	void deleteTransactionByIdfix(long toAccountNumber);
	List<Transaction> findTransactionByFromAccNumber(long accountId);
}
