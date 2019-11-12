package com.demobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.demobank.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	@Query(value = "delete from transaction where ToAccountNumber=:ToAccountNumber", nativeQuery = true) 
	@Modifying
	void deleteByIdfix(long ToAccountNumber);
}
