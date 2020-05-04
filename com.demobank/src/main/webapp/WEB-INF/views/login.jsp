<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@  taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="<c:url value='css/bootstrap.min.css'></c:url>">
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
body {
	background-color: whitesmoke;
	max-width: 700px;
	margin: auto;
}
</style>
<title>Welcome To Mean Bank</title>
</head>
<body>
	<h2>Welcome To Mean Bank</h2>
	<c:if test="${not empty errorMessage}">
		${errorMessage}
	</c:if>
	<div class="form-group">
		<form name='login' action='/login' method='POST'>
			<table>
				<tr>
					<td>Username:</td>
					<td><input type='text' name="username"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password'></td>
				</tr>

				<tr>
					<td><button class="btn btn-primary" type="submit" value="save">login</button></td>


					<td><a href="/userForm"> Sign Up</a></td>

				</tr>

			</table>
			<input type='hidden' name='${_csrf.parameterName}'
				value='${_csrf.token}' />
		</form>
	</div>

</body>
</html>