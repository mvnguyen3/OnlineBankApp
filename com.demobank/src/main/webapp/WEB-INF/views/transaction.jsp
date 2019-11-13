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
		if (document.getElementById("toggle").innerText == "Account") {
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
			document.getElementById("toggle").innerText = "Account";
		}
	} */
</script>
<title>Transaction Form</title>
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

	<% if(session.getAttribute("registered") != null){ %>
	<div class="form-group">
		<form:form action="saveTransaction" method="post"
			modelAttribute="transaction">

			<table>
				<tr>
					<th align="center" colspan="4">Transaction</th>
				</tr>
				<!-- Only 1 branch is available now -->
				<tr>
					<td>Account Id</td>
					<td><form:input type="text" name="fromAccountNumber"
							path="fromAccountNumber" value="${fromAccountNumber}"
							readonly="true" /></td>
					<td><form:errors cssClass="error" path="fromAccountNumber" /></td>
				</tr>
				<tr>
					<td>To Account Id</td>
					<td><form:input type="text" name="toAccountNumber"
							path="toAccountNumber" /></td>
					<td><form:errors cssClass="error" path="toAccountNumber" /></td>
				</tr>
				<tr>
					<td>Message</td>
					<td><form:input type="text" name="comment" path="comment" /></td>
					<td><form:errors cssClass="error" path="comment" /></td>
				</tr>

				<!-- Auto Set using java -->
				<%-- <tr>
					<td>Transaction Date Time</td>
					<td><form:input type="date" name="transactionDateTime"
							path="transactionDateTime" /></td>
					<td><form:errors cssClass="error" path="transactionDateTime" /></td>
				</tr> --%>
				<tr>
					<td>Transaction Type</td>
					<td><form:input name="transactionType" path="transactionType"
							placeholder="QuickPay" value="Quick Pay" readonly="true" />
					<td><form:errors cssClass="error" path="transactionType" /></td>
				</tr>
				<!-- Hide the submit button if user is Admin -->
				<% if(session.getAttribute("Admin") == null){ %>
				<tr>
					<td><button class="btn btn-primary" type="submit" value="save">Submit</button></td>
				</tr>
				<%} %>
			</table>
		</form:form>
	</div>

	<%}else{ %>
	<div>
		<h2>Customer Must Register :)</h2>
	</div>

	<%} %>

	<sec:authorize access="hasAuthority('admin')">
		<c:if test="${not empty transactions}">
			<div style="overflow: auto">
				<table border="1">
					<thead>
						<tr>
							<td>Account Id</td>
							<td>To Account Id</td>
							<td>Message</td>
							<th>Transaction Date</th>
							<th>Transaction Type</th>
							<th>Action</th>
						<tr>
					</thead>
					<c:forEach items="${transactions}" var="transaction">
						<tbody>
							<tr>
								<td>${transaction.fromAccountNumber}</td>
								<td>${transaction.toAccountNumber}</td>
								<td>${transaction.comment}</td>
								<td>${transaction.transactionDateTime}</td>
								<td>${transaction.transactionType}</td>
								<td><a
									href="/deleteTransaction?toAccountNumber=${transaction.toAccountNumber}">Delete</a></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</sec:authorize>
	<c:if test="${not empty status}">
		<img style="border-radius: 50%" src="images/zeno_customer.jpg"
		alt="Ultra Instict Goku" width="150" height="150" />
	${status}
	</c:if>


</body>
</html>


















































</html>