package com.demobank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demobank.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query(value="insert into user_role values(:userId, :roleId)", nativeQuery=true)
	@Modifying
	void saveUserRole(long userId, long roleId);
	
	@Query(value="select customer_customerId from customer_user where users_userId=:userId", nativeQuery=true)
	long getCustomerId(long userId);
	
	@Query(value="insert into role values(:roleId, :roleName)", nativeQuery=true)
	@Modifying
	void saveRole(long roleId, String roleName);
	
	@Query(value="select roleId from role", nativeQuery=true)
	List<Long> findRole();
	
	
}
















