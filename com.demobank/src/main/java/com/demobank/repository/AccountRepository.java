package com.demobank.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demobank.domain.Account;
public interface AccountRepository extends JpaRepository<Account, Long> {

}
