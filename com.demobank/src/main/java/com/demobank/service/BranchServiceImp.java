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
	public Branch saveBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public boolean branchExist(Branch branch) {

		return branchRepository.existsById(branch.getBranchId());
	}

	@Override
	public boolean branchExistById(long branchId) {
		
		return branchRepository.existsById(branchId);
	}

	@Override
	public void deleteBranchById(long branchId) {
		
		branchRepository.deleteById(branchId);
	}
	
	@Override
	public List<Branch> findAllBranch() {
	return branchRepository.findAll();
	}

	@Override
	public Branch findBranchById(long id) {
		
		return branchRepository.findById(id).get();
	}

}
