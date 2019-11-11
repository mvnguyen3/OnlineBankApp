package com.demobank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.demobank.domain.Account;
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query(value="insert into customer_Account values(:customerId, :accountId)", nativeQuery = true)
	@Modifying
	void saveAccountCustomer(long customerId, long accountId);
	
	@Query(value="delete from account where customerId=:customerId", nativeQuery = true)
	@Modifying
	void deleteAccountByCusId(long customerId);
}
