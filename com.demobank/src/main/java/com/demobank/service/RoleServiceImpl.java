package com.demobank.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository repository;

	@Override
	public void saveRole(long userId, long roleId) {
		repository.saveRole(userId, roleId);
	}

	@Override
	public long getCustomerId(long userId) {
		
		try {
			return repository.getCustomerId(userId);
		}catch(Exception e) {
			System.out.println("NO ID FOUND");
			return 0;
		}
		
	}

}
