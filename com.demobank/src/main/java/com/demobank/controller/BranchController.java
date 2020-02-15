package com.demobank.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demobank.domain.Branch;
import com.demobank.service.BranchService;

@Controller
public class BranchController {
	@Autowired
	BranchService service;
	
	@RequestMapping("/branchForm")
	ModelAndView customerForm(Branch branch, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("branchForm");
		
		if(session.getAttribute("registered") == null)
			return modelAndView;
		
		modelAndView.addObject("branches", service.findAllBranch());
		return modelAndView;
		
	}
	
	// Views include attributes
	private ModelAndView FormView(ModelAndView modelAndView) {
		modelAndView.setViewName("branchForm");
		modelAndView.addObject("branches", service.findAllBranch());
		return modelAndView;
	}
	
	@PostMapping("/saveBranch")
	ModelAndView saveForm(@ModelAttribute @Valid Branch branch, BindingResult br) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(service.findAllBranch());
		
		//For Validation
		if(br.hasErrors()) {
			System.out.println("Error");
			return FormView(modelAndView);
		}else {
			service.saveBranch(branch);
			modelAndView.addObject("status", "Successfully save id: " + branch.getBranchId());
			return FormView(modelAndView);
		}
		
	}
	
	@PostMapping("/initialize")
	ModelAndView saveForm() {
		ModelAndView modelAndView = new ModelAndView();
		List<Branch> branches = service.findAllBranch();
		if(branches.isEmpty()) {
			service.saveBranch(new Branch("US", "Il", "Chicago", "60630"));
			return FormView(modelAndView);	
		}
		
		else
			return FormView(modelAndView);			
	}
	
	
	
	// @ModelAttribute user is a need If you go back to the main page.
	@RequestMapping("/deleteBranch")
	ModelAndView delete(@ModelAttribute Branch branch, @RequestParam long branchId) {
		ModelAndView modelAndView = new ModelAndView();
		service.deleteBranchById(branchId);
		modelAndView.addObject("status", "Branch with id: " + branchId + " has been deleted");

		return FormView(modelAndView);
	}
	
}
























