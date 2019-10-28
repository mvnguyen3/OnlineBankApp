package com.demobank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.domain.Account;
import com.demobank.repository.AccountRepository;

@Service
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
	public void deleteAccountById(Long id) {
		// TODO Auto-generated method stub
		accountRepository.deleteById(id);
		
	}

}
