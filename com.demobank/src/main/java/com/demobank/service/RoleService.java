package com.demobank.service;

import java.util.List;

import com.demobank.domain.Role;

public interface RoleService{
	void saveLinkUserRole(long userId, long roleId);
	long getCustomerId(long userId);
	void saveRole(long roleId, String roleName);
	List<Long> findRole();
}









