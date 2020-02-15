package com.demobank.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demobank.domain.Account;
import com.demobank.domain.Transaction;
import com.demobank.service.TransactionService;
import com.demobank.validation.TransactionValidator;

@Controller
public class TransactionController {

	@Autowired
	TransactionService service;

	@Autowired
	TransactionValidator validator;

	@InitBinder
	void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(validator);
	}

	@RequestMapping("/transactionForm")
	ModelAndView welcome(Transaction transaction, HttpSession session) {
		ModelAndView model = new ModelAndView("/transaction");
		model.addObject("transactions",service.findAllTransactions());
		model.addObject("fromAccountNumber", transaction.getFromAccountNumber());

		return model;
	}

	// ************ VIEW HAS OBJECT ***************
	// Set back the View Which contains all the Object
	ModelAndView transactionFormView(ModelAndView modelAndView) {
		modelAndView.setViewName("transaction");
			modelAndView.addObject("transactions", service.findAllTransactions());

		return modelAndView;
	}
	// ************ VIEW HAS OBJECT ***************

	@PostMapping("/saveTransaction")
	ModelAndView saveTransaction(@ModelAttribute @Valid Transaction transaction, BindingResult br) {
		ModelAndView modelAndView = new ModelAndView();

		if (br.hasErrors()) {
			System.out.println("Error");
			return transactionFormView(modelAndView);
		} else {
			// Set Date time
			Instant instant = Instant.now();
			Date date = Date.from(instant);
			transaction.setTransactionDateTime(date);

			service.saveTransaction(transaction);
			modelAndView.addObject("status", "Successfully save transaction");
		}

		return transactionFormView(modelAndView);
	}

	// @ModelAttribute user is a need If you go back to the main page.
	@RequestMapping("/deleteTransaction")
	ModelAndView delete(@ModelAttribute Transaction transaction, @RequestParam long toAccountNumber) {
		ModelAndView modelAndView = new ModelAndView();
		service.deleteTransactionByIdfix(toAccountNumber);
		modelAndView.addObject("status", "Transaction to id: " + toAccountNumber + " has been deleted");
		System.out.println("Deleted transaction id: " + toAccountNumber);

		return transactionFormView(modelAndView);
	}

}
