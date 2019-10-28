package com.demobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demobank.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
