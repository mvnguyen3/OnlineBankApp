package com.demobank.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.demobank.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(long userId);
	
	@Query("delete from user where userId=:userId")
	@Modifying
	void deleteByUserId(long userId);
	


				
}










