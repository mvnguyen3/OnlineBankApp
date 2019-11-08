package com.demobank.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService (name="userWebService")
public interface UserWebService {
	@WebMethod
	public @WebResult UserResponse createUser(@WebParam UserCreateRequest userCreateRequest);
	
	@WebMethod
	public @WebResult UserResponse getUserById(@WebParam UserRequest userRequest);
	
	@WebMethod
	public @WebResult UserResponse getAllUsers();
	
	@WebMethod
	public @WebResult UserResponse deleteUserById(@WebParam UserRequest userRequest);
}
