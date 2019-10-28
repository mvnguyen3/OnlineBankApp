package com.demobank.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	@Id
	private long roleId;
	
	private String rolename;
	
	@ManyToMany(mappedBy = "userRoles")
	private Set<User> roleUsers = new HashSet<>();
	
	
	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	Role(String rolename){
		this.rolename = rolename;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<User> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Set<User> roleUsers) {
		this.roleUsers = roleUsers;
	}
	
	
	
}
