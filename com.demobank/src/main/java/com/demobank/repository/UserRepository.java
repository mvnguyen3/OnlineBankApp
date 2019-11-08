package com.demobank.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.demobank.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(long userId);
	
	@Query(value="select * from user where username=:name", nativeQuery = true)
	User findByUsername(String name);
	
	@Query(value="select max(userId) from user", nativeQuery = true)
	long getMaxId();

	@Query(value="select * from user where userEmail=:email", nativeQuery=true)
	User findUserByEmail(String email);

				
}










