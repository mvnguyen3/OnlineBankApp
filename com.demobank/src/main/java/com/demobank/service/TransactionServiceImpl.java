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
	public void save(Transaction transaction) {
		repository.save(transaction);
	}

	@Override
	public List<Transaction> findAll() {
		
		return repository.findAll();
	}

	@Override
	public void deleteByIdfix(long ToAccountNumber) {
		repository.deleteByIdfix(ToAccountNumber);
		
	}

}
