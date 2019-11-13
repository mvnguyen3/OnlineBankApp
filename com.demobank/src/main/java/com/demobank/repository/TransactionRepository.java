package com.demobank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.demobank.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	@Query(value = "delete from transaction where toAccountNumber=:toAccountNumber", nativeQuery = true) 
	@Modifying
	void deleteByIdfix(long toAccountNumber);
	
	
	@Query(value="select * from transaction where fromAccountNumber=:accountId", nativeQuery = true)
	List<Transaction> findByFromAccNumber(long accountId);
	
}
