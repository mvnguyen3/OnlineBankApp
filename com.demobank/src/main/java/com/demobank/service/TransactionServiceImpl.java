package com.demobank.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.domain.Transaction;
import com.demobank.repository.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository repository;
	
	@Override
	public void saveTransaction(Transaction transaction) {
		repository.save(transaction);
	}

	@Override
	public List<Transaction> findAllTransactions() {
		
		return repository.findAll();
	}

	@Override
	public void deleteTransactionByIdfix(long toAccountNumber) {
		repository.deleteByIdfix(toAccountNumber);
		
	}

	@Override
	public List<Transaction> findTransactionByFromAccNumber(long accountId) {		
		return repository.findByFromAccNumber(accountId);
	}

}
