package com.demobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demobank.domain.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
//	@Query()
//	Branch findByBranchId(long id);
}
