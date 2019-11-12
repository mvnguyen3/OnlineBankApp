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


<script type="text/javascript">
	function addDashesPhone(f) {
		var r = /(\D+)/g, npa = '', nxx = '', last4 = '';
		f.value = f.value.replace(r, '');
		npa = f.value.substr(0, 3);
		nxx = f.value.substr(3, 3);
		last4 = f.value.substr(6, 4);
		f.value = npa + '-' + nxx + '-' + last4;
	}

	/* 	function toggle(f) {
	 if (document.getElementById("toggle").innerText == "Navigation") {
	 document.getElementById("userBtn").style.display = "inline-block";
	 document.getElementById("customerBtn").style.display = "inline-block";
	 document.getElementById("accountBtn").style.display = "inline-block";
	 document.getElementById("branchBtn").style.display = "inline-block";
	 document.getElementById("toggle").innerText = "x";
	 }
	 }
	 function toggleOut(f) {
	 if (document.getElementById("toggle").innerText == "x") {
	 document.getElementById("userBtn").style.display = "none";
	 document.getElementById("customerBtn").style.display = "none";
	 document.getElementById("accountBtn").style.display = "none";
	 document.getElementById("branchBtn").style.display = "none";
	 document.getElementById("toggle").innerText = "Navigation";
	 }
	 } */
</script>
<title>User Form</title>
<style>
body {
	background-color: whitesmoke;
	max-width: 700px;
	margin: auto;
}

/* #userBtn {
	display: none;
}

#customerBtn {
	display: none;
}

#accountBtn {
	display: none;
}

#branchBtn {
	display: none;
}

#toggle {
	display: inline-block;
	background: "blue";
} */
</style>
</head>
<body>
	<div class="Navigation-bar">
		<table>
			<thead>
				<tr>
					<th align="center" colspan="4">BANKING APPLICATION</th>

				</tr>

				<sec:authorize access="isAuthenticated()">
					<tr>
						<th align="center" colspan="4"><a href="/login?logout">Logout
								${pageContext.request.userPrincipal.authorities[0]}</a></th>
					</tr>
				</sec:authorize>

			</thead>
			<tbody>

				<tr>
					<%
						if (session.getAttribute("newUser") != null) {
					%>

					<td>
					<td><a href="/"><button id="userBtn"
								class="btn btn-primary">Login</button></a></td>
					</td>
					<%
						}
					%>

					<sec:authorize access="isAuthenticated()">
						<td><a href="/userForm"><button id="userBtn"
									class="btn btn-primary">User</button></a></td>

						<td><a href="/customerForm"><button id="customerBtn"
									class="btn btn-primary">Customer</button></a></td>
						<td><a href="/accountForm"><button id="accountBtn"
									class="btn btn-primary">Account</button></a></td>
						
						<td><a href="/branchForm"><button id="branchBtn"
									class="btn btn-primary">Branch</button></a></td>
					</sec:authorize>
				</tr>

			</tbody>
		</table>
	</div>

	<div class="form-group">
		<form:form action="saveUserForm" method="post" modelAttribute="user">
			<table>
				<tr>
					<th align="center" colspan="4">Registration Form</th>
				</tr>
				<tr>
					<td>User Id</td>
					<td><form:input type="text" name="userId" path="userId"
							readonly="true" /></td>
					<td><form:errors cssClass="error" path="userId" /></td>
				</tr>
				<tr>
					<td>Username</td>
					<td><form:input type="text" name="username" path="username" /></td>
					<td><form:errors cssClass="error" path="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:input type="password" name="password"
							path="password" /></td>
					<td><form:errors cssClass="error" path="password" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input type="text" name="userEmail" path="userEmail"
							value="@gmail.com" /></td>
					<td><form:errors cssClass="error" path="userEmail" /></td>
				</tr>

				<tr>
					<td>Mobile</td>
					<td><form:input type="text" maxlength="12" name="userMobile"
							placeholder="773-202-xxxx" path="userMobile"
							onkeypress="addDashesPhone(this)" /></td>
					<td><form:errors cssClass="error" path="userMobile" /></td>
				</tr>
				<!-- Hide the content if user is Admin  -->
				<%
					if (session.getAttribute("Admin") == null) {
				%>
				<tr>
					<td><button class="btn btn-primary" type="submit" value="save">Submit</button></td>
				</tr>
				<%
					}
				%>
			</table>
		</form:form>
	</div>
	<!--Display the user only the login role is admin or manager -->
	<sec:authorize access="hasAuthority('admin')">

		<c:if test="${not empty users}">

			<div style="overflow: auto">
				<table border="1">
					<tr>
						<th>User Id</th>

						<th>Email</th>
						<th>Mobile</th>
						<th>Action</th>
					</tr>
					<c:forEach items="${users}" var="user" begin="1">
						<tr>
							<td>${user.userId}</td>
							<td>${user.userEmail}</td>
							<td>${user.userMobile}</td>
							<!-- update button will redirect to a new jsp page -->
							<td>
								<!-- <a --> <%-- href="/updateUser?userId=${user.userId}&username=${user.username}&password=${user.password}&userEmail=${user.userEmail}&userMobile=${user.userMobile}">Update</a>
								&nbsp; &nbsp; &nbsp; --%> <a
								href="/deleteUser?userId=${user.userId}">Delete</a>
							</td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</c:if>
	</sec:authorize>
	<%-- <%=session.getAttribute("user") %> --%>
	<!-- Ternary Operator  -->
	${status}
	<!-- <div>
		Note Center:
			- Don't delete user.....
	</div> -->
</body>
</html>

















