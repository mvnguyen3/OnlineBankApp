package com.demobank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demobank.domain.Account;

import com.demobank.service.AccountService;



@Controller
public class AccountController {
	
	@Autowired 
	AccountService service;
	
	@RequestMapping("/accountForm")
	ModelAndView customerForm(Account account) {
		ModelAndView modelAndView = new ModelAndView("accountForm");
		modelAndView.addObject("accounts", service.findAllAccount());
		return modelAndView;
		
	}
	
	// Views include attributes
	private ModelAndView accountFormView(ModelAndView modelAndView) {
		modelAndView.setViewName("accountForm");
		modelAndView.addObject("accounts", service.findAllAccount());
		return modelAndView;
	}
	
	@PostMapping("/saveAccount")
	ModelAndView saveForm(@ModelAttribute @Valid Account account, BindingResult br) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(service.findAllAccount());
		
		//For Validation
		if(br.hasErrors()) {
			System.out.println("Error");
			return accountFormView(modelAndView);
		}else {
			service.saveAccount(account);
			modelAndView.addObject("status", "Successfully save id: " + account.getAccountID());
			return accountFormView(modelAndView);
		}
		
	}
	
	// @ModelAttribute user is a need If you go back to the main page.
	@RequestMapping("/deleteAccount")
	ModelAndView delete(@ModelAttribute Account account, @RequestParam long accountId) {
		ModelAndView modelAndView = new ModelAndView();
		service.deleteAccountById(accountId);
		modelAndView.addObject("status", "Customer with id: " + accountId + " has been deleted");

		return accountFormView(modelAndView);
	}
		
}




































