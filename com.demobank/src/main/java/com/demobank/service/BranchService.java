package com.demobank.service;

import java.util.List;

import com.demobank.domain.Branch;

public interface BranchService {
	Branch findBranchById(long id);
	public Branch saveBranch(Branch branch);
	public boolean branchExist(Branch branch);
	public boolean branchExistById(long branchId);
	public void deleteBranchById(long branchId);
	List<Branch> findAllBranch();
}
