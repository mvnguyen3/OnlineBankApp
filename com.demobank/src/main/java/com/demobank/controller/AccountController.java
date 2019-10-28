package com.demobank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demobank.domain.Account;
import com.demobank.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@PostMapping("/saveAccount")
	ResponseEntity<?> saveAccount(@RequestBody Account account){
		// Check if the account is already exists
		if(!accountService.exist(account.getAccountID())) 
			return new ResponseEntity<Account>(accountService.saveAccount(account), HttpStatus.OK);			
		return new ResponseEntity<String>("The account id:" + account.getAccountID() + " exists !!!", HttpStatus.FAILED_DEPENDENCY);			
	}
	
	@GetMapping("/showAccount")
	ResponseEntity<?> showAccount(){
		return new ResponseEntity<List<Account>>(accountService.findAllAccount(), HttpStatus.OK);
	}
	
	@PostMapping("/deleteAccount")
	ResponseEntity<?> deleteAccount(@RequestBody Long id){
		if(accountService.exist(id)) {
			accountService.deleteAccountById(id);
			return new ResponseEntity<String>("The account with id:" + id + " has been deleted", HttpStatus.OK);
		}
		return new ResponseEntity<List<Account>>(accountService.findAllAccount(), HttpStatus.OK);
		
	}
	
}


















