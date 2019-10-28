package com.demobank.service;

import java.util.List;

import com.demobank.domain.Branch;

public interface BranchService {
	public Branch save(Branch branch);
	public boolean exist(Branch branch);
	public boolean existById(long branchId);
	public void deleteById(long branchId);
	List<Branch> findAllBranch();
}
