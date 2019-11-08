package com.demobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.demobank.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

		@Query(value="insert into customer_user values(:customerId, :userId)", nativeQuery=true)
		@Modifying
		void saveCustomerUser(long customerId, long userId);
		
		@Query(value="select * from customer where customerEmail=:email", nativeQuery=true)
		Customer findByEmail(String email);
		
		@Query(value="select * from customer where customerName=:name", nativeQuery=true)
		Customer findByName(String name);
		
		@Query(value="delete from customer_user where customer_customerId=:id", nativeQuery=true)
		@Modifying
		void deleteUserLink(long id);
		
		
		@Query(value="truncate table customer", nativeQuery=true)
		@Modifying
		void truncate();
		
}
