package com.demobank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	JavaMailSender mailSender;
	
	public void sendMail(String emailTo, String from, String description) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailTo);
		message.setFrom(from);
		message.setSubject("Customer Feedback");
		message.setText(description);
		
		mailSender.send(message);
		
		System.out.println("Sucessfully send message");
		
	}
	
}
