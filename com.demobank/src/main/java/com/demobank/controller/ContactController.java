package com.demobank.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.demobank.service.MailServiceImpl;

@Controller
public class ContactController {

	@Autowired
	MailServiceImpl mailservice;
	
	@RequestMapping("/contact")
	ModelAndView mainView() {
		ModelAndView model = new ModelAndView("contact");
		
		return model;
	}
	
	ModelAndView mainView1(ModelAndView model) {
		model.setViewName("contact");
		
		return model;
	}
	
	
	@PostMapping("/sendMail")
	ModelAndView send(@RequestParam String email, @RequestParam String description) {
		ModelAndView model = new ModelAndView();
		System.out.println("email: " + email + " description: " + description);
		String destinationEmail = "synergyit171@gmail.com";
		mailservice.sendMail(destinationEmail, email, description);
		model.addObject("status", "Thank you!! \nOur team will reach out to you as soon as possible");
		
		
		return mainView1(model);
	}
}












