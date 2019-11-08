package com.demobank.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demobank.domain.Role;
import com.demobank.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		System.out.println("@UserDetailsServiceImpl.loadUserByUsername(String name).........name: "+name);
		com.demobank.domain.User user = userRepository.findByUsername(name);
		
		Set<GrantedAuthority> demobankGa = new HashSet<>();
		
		for ( Role role: user.getUserRoles()){
			demobankGa.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), demobankGa);
	}

}

















