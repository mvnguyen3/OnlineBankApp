package com.demobank.service;

import java.util.List;

import com.demobank.domain.Account;

public interface AccountService {
	Account saveAccount(Account account);
	List<Account> findAllAccountByCusId(long customerLinkedId);
	boolean accountExist(long id);
	List<Account> findAllAccount();
	void deleteAccountById(long id);
	void deleteAccountByCusId(long customerId);
	void saveLinkAccountCustomer(long customerId, long accountId);
	
}
