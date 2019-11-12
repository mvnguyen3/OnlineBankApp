package com.demobank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.domain.Branch;
import com.demobank.repository.BranchRepository;

@Service
public class BranchServiceImp implements BranchService {

	@Autowired
	BranchRepository branchRepository;

	@Override
	public Branch save(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public boolean exist(Branch branch) {

		return branchRepository.existsById(branch.getBranchId());
	}

	@Override
	public boolean existById(long branchId) {
		
		return branchRepository.existsById(branchId);
	}

	@Override
	public void deleteById(long branchId) {
		
		branchRepository.deleteById(branchId);
	}
	
	@Override
	public List<Branch> findAllBranch() {
	return branchRepository.findAll();
	}

	@Override
	public Branch findById(long id) {
		
		return branchRepository.findById(id).get();
	}

}
