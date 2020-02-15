package com.demobank.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demobank.domain.Account;
import com.demobank.domain.Transaction;
import com.demobank.service.AccountService;

@Component
public class TransactionValidator implements Validator {

	@Autowired
	AccountService accService;

	@Override
	public boolean supports(Class<?> clazz) {

		return Transaction.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Transaction transaction = (Transaction) target;

		long toAccId = transaction.getToAccountNumber();
		List<Account> accounts = accService.findAllAccount();
		boolean idExist = false;
		for (Account acc : accounts) {
			if (acc.getAccountID() == toAccId) {
				idExist = true;
			}
		}

		try {
			if (!idExist) {
				errors.rejectValue("toAccountNumber", "transaction.toAccountNumber.notExists",
						transaction.getToAccountNumber() + " Does not exist !!");

			}
			if (transaction.getFromAccountNumber() == transaction.getToAccountNumber()) {
				errors.rejectValue("toAccountNumber", "transaction.toAccountNumber.exists",
						transaction.getToAccountNumber() + " Can't be the same as From Account Id!!");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
