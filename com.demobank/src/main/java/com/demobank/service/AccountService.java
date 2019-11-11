package com.demobank.service;

import java.util.List;

import com.demobank.domain.Account;

public interface AccountService {
	Account saveAccount(Account account);
	boolean exist(long id);
	List<Account> findAllAccount();
	void deleteAccountById(long id);
	void deleteAccountByCusId(long customerId);
	void saveAccountCustomer(long customerId, long accountId);
	
}
