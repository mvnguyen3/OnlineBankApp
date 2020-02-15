<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@  taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

<script type="text/javascript">
	function addDashesPhone(f) {
		var r = /(\D+)/g, npa = '', nxx = '', last4 = '';
		f.value = f.value.replace(r, '');
		npa = f.value.substr(0, 3);
		nxx = f.value.substr(3, 3);
		last4 = f.value.substr(6, 4);
		f.value = npa + '-' + nxx + '-' + last4;
	}
	/* function toggle(f) {
		if (document.getElementById("toggle").innerText == "Customer") {
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
			document.getElementById("toggle").innerText = "Customer";
		}
	} */
</script>
<title>Customer Form</title>
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
					<th colspan="4">BANKING APPLICATION</th>
				</tr>

				<tr>
					<th align="center" colspan="4"><a href="/login?logout">Logout
							${pageContext.request.userPrincipal.authorities[0]} </a></th>
				</tr>

			</thead>
			<tbody>
				<tr>
					<!-- For Admin use only  -->
					<sec:authorize access="hasAuthority('admin')">
						<td><a href="/userForm"><button id="userBtn"
									class="btn btn-primary">User</button></a></td>
					</sec:authorize>

					<td><a href="/customerForm"><button id="customerBtn"
								class="btn btn-primary">Customer</button></a></td>
					<td><a href="/accountForm"><button id="accountBtn"
								class="btn btn-primary">Account</button></a></td>
					<td><a href="/branchForm"><button id="branchBtn"
								class="btn btn-primary">Branch</button></a></td>

					<sec:authorize access="hasAuthority('user')">
						<td><a href="/contact"><button id="branchBtn"
									class="btn btn-primary">Contact</button></a></td>
					</sec:authorize>
				</tr>

			</tbody>
		</table>
	</div>

	<!-- If the customer already having a customer account  -->
	<%
		if (session.getAttribute("registered") == null || session.getAttribute("Admin") != null) {
	%>
	<div class="form-group">
		<form:form action="saveCustomer" method="post"
			modelAttribute="customer">

			<table>
				<tr>
					<th align="center" colspan="4">Customer Form</th>
				</tr>
				<tr>
					<td>Name</td>
					<td><form:input type="text" name="customerName"
							path="customerName" /></td>
					<td><form:errors cssClass="error" path="customerName" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input type="text" name="customerEmail"
							value='<%=session.getAttribute("email")%>' path="customerEmail"
							readonly="true" /></td>
					<td><form:errors cssClass="error" path="customerEmail" /></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><form:input type="text" name="customerPhone"
							placeholder="773-202-xxxx" onkeypress="addDashesPhone(this)"
							maxlength="12" path="customerPhone" /></td>
					<td><form:errors cssClass="error" path="customerPhone" /></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><form:radiobutton name="customerGender"
							path="customerGender" value="male" label="male" checked="true" />
						<form:radiobutton name="customerGender" path="customerGender"
							value="female" label="female" /> <form:radiobutton
							name="customerGender" path="customerGender"
							value="rather not say" label="N/A" /></td>
				</tr>
				<tr>
					<td>SSN</td>
					<td><form:input type="text" name="customerSsn"
							placeholder="181-93-xxxx" onkeypress="addDashesPhone(this)"
							maxlength="11" path="customerSsn" /></td>
					<td><form:errors cssClass="error" path="customerSsn" /></td>
				</tr>
				<tr>
					<td>Date Of Birth</td>
					<td><form:input type="date" name="customerDob"
							path="customerDob" /></td>
					<td><form:errors cssClass="error" path="customerDob" /></td>
				</tr>

				<!-- Testing -->

				<tr>
					<td>Line1</td>
					<td><form:input type="text" name="Address.line1"
							path="Address.line1" /></td>
					<td><form:errors cssClass="error" path="Address.line1" /></td>
				</tr>
				<tr>
					<td>Line2</td>
					<td><form:input type="text" name="Address.line2"
							path="Address.line2" value="N/A" /></td>
					<td><form:errors cssClass="error" path="Address.line2" /></td>
				</tr>
				<tr>
					<td>Country</td>
					<td><form:input type="text" name="Address.country"
							path="Address.country" value="US" readonly="true" /></td>
					<td><form:errors cssClass="error" path="Address.country" /></td>
				</tr>
				<tr>
					<td>State</td>
					<td><form:input type="text" name="Address.state"
							path="Address.state" value="Illinois" readonly="true" /></td>
					<td><form:errors cssClass="error" path="Address.state" /></td>
				</tr>
				<tr>
					<td>City</td>
					<td><form:input type="text" name="Address.city"
							path="Address.city" /></td>
					<td><form:errors cssClass="error" path="Address.city" /></td>
				</tr>
				<!-- Testing -->
				<!-- Hide the submit button if user is Admin -->
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
	<%
		} else {
	%>


	<img style="border-radius: 50%" src="images/zeno_customer.jpg"
		alt="Ultra Instict Goku" width="150" height="150" />
	<h3>Zeno said:</h3>
	<p>The customer is already having an account</p>

	<%
		}
	%>

	<sec:authorize access="hasAuthority('admin')">
		<c:if test="${not empty customers}">
			<div style="overflow: auto">
				<table border="1">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Gender</th>
						<th>SSN</th>
						<th>DOB</th>
						<th>Action</th>
					<tr>
						<c:forEach items="${customers}" var="customer">

							<tr>
								<td>${customer.customerId}</td>
								<td>${customer.customerName}</td>
								<td>${customer.customerEmail}</td>
								<td>${customer.customerPhone}</td>
								<td>${customer.customerGender}</td>
								<td>${customer.customerSsn}</td>
								<td>${customer.customerDob}</td>
								<td>
									<%-- <a
									href="/updateCustomer?customerId=${customer.customerId}&customerName=${customer.customerName}
							&customerEmail=${customer.customerEmail}
							&customerPhone=${customer.customerPhone}&customerGender=${customer.customerGender}
							&customerSsn=${customer.customerSsn}&customerDob=${customer.customerDob}">Update
								</a>  &nbsp; &nbsp; &nbsp; --%> <a
									href="/deleteCustomer?customerId=${customer.customerId}">Delete</a>
								</td>

							</tr>

						</c:forEach>
				</table>
			</div>
		</c:if>
	</sec:authorize>
	<%-- <%=session.getAttribute("user")%> --%>

<%-- 	<c:if test="${not empty status}">
		<img style="border-radius: 50%" src="images/zeno_customer.jpg"
		alt="Ultra Instict Goku" width="150" height="150" />
	${status}
	</c:if> --%>
	
	<%
		try {
			if (session.getAttribute("status").toString().equalsIgnoreCase("failed")) {
	%>
	<img style="border-radius: 50%" src="images/beerus_hakai.png"
		alt="beerus" width="150" height="150" />
	<%
		session.removeAttribute("status");
		} else {
	%>

	<img style="border-radius: 50%" src="images/zeno_customer.jpg"
		alt="Zeno" width="150" height="150" />
	<%
	session.removeAttribute("status");
		}
		} catch (NullPointerException ne) {
			System.out.println("Null Pointer Exception");
		}
	%>
	${status}

</body>
</html>




























