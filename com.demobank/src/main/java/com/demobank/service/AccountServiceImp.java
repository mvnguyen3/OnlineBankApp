package com.demobank.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.domain.Account;
import com.demobank.repository.AccountRepository;

@Service
@Transactional
public class AccountServiceImp implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account saveAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}

	@Override
	public boolean exist(long id) {
		// TODO Auto-generated method stub
		return accountRepository.existsById(id);
	}

	@Override
	public List<Account> findAllAccount() {
		// TODO Auto-generated method stub				
		return accountRepository.findAll();
	}

	@Override
	public void deleteAccountById(long id) {

		accountRepository.deleteById(id);
		
	}

	@Override
	public void saveAccountCustomer(long customerId, long accountId) {
		accountRepository.saveAccountCustomer(customerId, accountId);
		
	}


	@Override
	public void deleteAccountByCusId(long customerId) {
		accountRepository.deleteAccountByCusId(customerId);
		
	}

}
