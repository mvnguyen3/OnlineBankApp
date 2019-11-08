
<%@page import="com.demobank.service.UserService"%>
<%@page import="com.demobank.domain.User"%>
<%@page import="com.demobank.service.UserServiceImp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@  taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<title>Welcome ${pageContext.request.userPrincipal.name}</title>
<style>
body {
	background-color: whitesmoke;
	max-width: 700px;
	margin: auto;
}
</style>
</head>

<body>
	<p>
		<a href="/login?logout">Logout
			${pageContext.request.userPrincipal.authorities[0]}</a>
	</p>
	<%-- <p>
		<!-- Use to check the role of the user -->
	<p>${pageContext.request.userPrincipal}</p> --%>

	

<%-- 
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.authorities" />
	</sec:authorize> --%>

	<%-- <div>
		Authenticated as
		<sec:authentication property="authorities[0]" />
	</div> --%>

	

	<div>
		<sec:authorize access="hasAuthority('admin')">
			<p>
				Welcome <b>${pageContext.request.userPrincipal.name}</b>
			<p>
			<p>
				<%-- <a href="/userForm?username=${pageContext.request.userPrincipal.name}">User Form</a> --%>
				<a href="/userForm">User Form</a>
			</p>
		</sec:authorize>
	</div>

	<div>
		<sec:authorize access="hasAuthority('user')">
			<p>
				Welcome <b>${pageContext.request.userPrincipal.name}</b>
			<p>
				<%-- <a href="/customerForm?customerName=${pageContext.request.userPrincipal.name}">Customer Form</a> --%>
				<a href="/customerForm">Customer Form</a>
			</p>

		</sec:authorize>
	</div>
</body>
</html>























