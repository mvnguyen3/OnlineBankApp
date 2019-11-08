package com.demobank.ws;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCreateRequest {
	
	@XmlElement(name="branchId")
	private long userId;
		
	private String username;
	
	private String password;
	
	private String userEmail;

	private String userMobile;
	
	
	
	public UserCreateRequest() {
		
	}



	public UserCreateRequest(long userId, String username, String password, String userEmail, String userMobile) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userEmail = userEmail;
		this.userMobile = userMobile;
	}



	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	public String getUserMobile() {
		return userMobile;
	}



	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}



	@Override
	public String toString() {
		return "UserCreateRequest [userId=" + userId + ", username=" + username + ", password=" + password
				+ ", userEmail=" + userEmail + ", userMobile=" + userMobile + "]";
	}
	
	
	
}




























