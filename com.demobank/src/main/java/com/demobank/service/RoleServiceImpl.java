package com.demobank.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.domain.Role;
import com.demobank.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository repository;

	@Override
	public void saveLinkUserRole(long userId, long roleId) {
		repository.saveUserRole(userId, roleId);
	}

	@Override
	public long getCustomerId(long userId) {

		try {
			return repository.getCustomerId(userId);
		} catch (Exception e) {
			System.out.println("NO ID FOUND");
			return 0;
		}

	}

	@Override
	public void saveRole(long roleId, String roleName) {
		repository.saveRole(roleId, roleName);

	}

	@Override
	public List<Long> findRole() {
		try {
			return repository.findRole();
		} catch (NullPointerException e) {
			return null;
		}
	}

}
