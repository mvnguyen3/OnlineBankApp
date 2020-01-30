package com.demobank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demobank.domain.Branch;
import com.demobank.service.BranchService;
import com.demobank.service.UnifiedService;

// RestController is for postman

@RestController
@RequestMapping("/BranhR")
public class BranchRestController {
	@Autowired
	UnifiedService service;
	
	
	// Save Branch to database
	@PostMapping("/saveBranch")
	public ResponseEntity<?> saveBranch(@RequestBody Branch branch) {
		if(!service.branchExist(branch)) {
			service.saveBranch(branch);
			return new ResponseEntity<Branch>(branch, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("Branch id: " + branch.getBranchId() + " is already exist", HttpStatus.FAILED_DEPENDENCY);
		}				
	}	
	
	@PostMapping("/deleteBranch")
	public ResponseEntity<?> deleteBranch(@RequestBody long branchId){
		if(!service.branchExistById(branchId)) {
			return new ResponseEntity<String>("Branch with id: " + branchId + " doesn not exist!", HttpStatus.FAILED_DEPENDENCY);
		}		
		service.deleteBranchById(branchId);
		return new ResponseEntity<String>("Sucessfully deleted id: " + branchId, HttpStatus.OK);		
	}
	
	@GetMapping("/showBranch")
	public ResponseEntity<?> showBranch(){
		return new ResponseEntity<List<Branch>>(service.findAllBranch(), HttpStatus.OK);
	}
}

















