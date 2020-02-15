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
<title>Account Form</title>
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
					<sec:authorize access="hasAuthority('admin')">
						<td><a href="/transactionForm"><button
									id="transactionBtn" class="btn btn-primary">Transaction</button></a></td>
					</sec:authorize>
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

	<%
	if (session.getAttribute("registered") != null)  {
	%>
	<div class="form-group">
		<form:form action="saveAccount" method="post" modelAttribute="account">

			<table>
				<tr>
					<th align="center" colspan="4">Account Form</th>
				</tr>
				<!-- Only 1 branch is available now -->
				<tr>
					<td>Branch Id</td>
					<td>1</td>
				</tr>
				<tr>
					<td>Type</td>
					<td><form:radiobutton name="accountType" path="accountType"
							value="SAVING" label="Saving" /> <form:radiobutton
							name="accountType" path="accountType" value="CHECKING"
							label="Checking" /> <form:radiobutton name="accountType"
							path="accountType" value="CREDIT" label="Credit" /> <form:radiobutton
							name="accountType" path="accountType" value="DEBIT" label="Debit" /></td>
					<td><form:errors cssClass="error" path="accountType" /></td>
				</tr>
				<tr>
					<td>Holder</td>
					<td><form:input type="text" name="accountHolder"
							path="accountHolder" value="${holder}" readonly="true" /></td>
					<td><form:errors cssClass="error" path="accountHolder" /></td>
				</tr>
				<tr>
					<td>Open Date</td>
					<td><form:input type="date" name="accountOpenDate"
							path="accountOpenDate" /></td>
					<td><form:errors cssClass="error" path="accountOpenDate" /></td>
				</tr>
				<tr>
					<td>Balance</td>
					<td><form:input name="accountBalance" path="accountBalance"
							placeholder="$12,000" />
					<td><form:errors cssClass="error" path="accountBalance" /></td>
				</tr>
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
	<div>
		<h2>Customer Must Register :)</h2>
	</div>

	<%
		}
	%>
	<sec:authorize access="hasAuthority('user')">
		<c:if test="${not empty currentAccounts}">
		
			<div style="overflow: auto; height: 400px">
				<table border="1">
					<thead>
						<tr>
							<th>Id</th>
							<!-- <th>Cus_Id</th>
							<th>Bran_Id</th> -->
							<th>Type</th>
							<th>Holder</th>
							<th>Open Date</th>
							<th>Balance</th>
							<th>Action</th>
						<tr>
					</thead>
					<c:forEach items="${currentAccounts}" var="account">
						<tbody>
							<tr>
								<td>${account.accountID}</td>
								<%-- <td>${account.accountCustomers.customerId}</td>
								<td>${account.accountBranch.branchId}</td> --%>
								<td>${account.accountType}</td>
								<td>${account.accountHolder}</td>
								<td>${account.accountOpenDate}</td>
								<td>$${account.accountBalance}</td>
								<td><a
									href="/transactionForm?fromAccountNumber=${account.accountID}">Transfer
										To</a></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</sec:authorize>

	<sec:authorize access="hasAuthority('admin')">
		<c:if test="${not empty accounts}">
			<div style="overflow: auto; height: 400px">
				<table border="1">
					<thead>
						<tr>
							<th>Id</th>
							<th>Cus_Id</th>
							<th>Bran_Id</th>
							<th>Type</th>
							<th>Holder</th>
							<th>Open Date</th>
							<th>Balance</th>
							<th>Action</th>
						<tr>
					</thead>
					<c:forEach items="${accounts}" var="account">
						<tbody>
							<tr>
								<td>${account.accountID}</td>
								<td>${account.accountCustomers.customerId}</td>
								<td>${account.accountBranch.branchId}</td>
								<td>${account.accountType}</td>
								<td>${account.accountHolder}</td>
								<td>${account.accountOpenDate}</td>
								<td>$${account.accountBalance}</td>
								<td><a href="/deleteAccount?accountId=${account.accountID}">Delete</a></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</sec:authorize>

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

















































