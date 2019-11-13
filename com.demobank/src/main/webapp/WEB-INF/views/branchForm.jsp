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
	function toggle(f) {
		if (document.getElementById("toggle").innerText == "Branch") {
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
			document.getElementById("toggle").innerText = "Branch";
		}
	}
</script>
<title>Branch Form</title>
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
							${pageContext.request.userPrincipal.authorities[0]}</a></th>
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


	<%
		if (session.getAttribute("registered") != null) {
	%>
	<div class="form-group">
		<form:form action="saveBranch" method="post" modelAttribute="branch">

			<table>
				<tr>
					<th align="center" colspan="4">Branch Form</th>
				</tr>
				<tr>
					<td>Country</td>
					<td><form:radiobutton name="branchCountry"
							path="branchCountry" value="USA" label="USA" checked="true" /></td>
					<td><form:errors cssClass="error" path="branchCountry" /></td>
				</tr>
				<tr>
					<td>State</td>
					<td><form:radiobutton name="branchState" path="branchState"
							value="Illinois" label="Illinois" checked="true" /></td>
					<td><form:errors cssClass="error" path="branchState" /></td>
				</tr>

				<tr>
					<td>City</td>
					<td><form:select name="branchCity" path="branchCity">
							<option value="Chicago">Chicago</option>
							<option value="Ohio">Ohio</option>
							<option value="Rockford">Rockford</option>
							<option value="Joliet">Joliet</option>
						</form:select></td>

					<td><form:errors cssClass="error" path="branchCity" /></td>
				</tr>

				<tr>
					<td>Zipcode</td>
					<td><form:input type="text" name="branchZipcode"
							path="branchZipcode" /></td>
					<td><form:errors cssClass="error" path="branchZipcode" /></td>
				</tr>

				<!-- Allow for admin uses -->
				<%
					if (session.getAttribute("Admin") != null) {
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


	<c:if test="${not empty branches}">
		<div style="overflow: auto">
			<table border="1">
				<thead>
					<tr>
						<th>Id</th>
						<th>Country</th>
						<th>State</th>
						<th>City</th>
						<th>Zipcode</th>
						<!-- <th>Action</th> -->
					<tr>
				</thead>
				<c:forEach items="${branches}" var="branch">
					<tbody>
						<tr>
							<td>${branch.branchId}</td>
							<td>${branch.branchCountry}</td>
							<td>${branch.branchState}</td>
							<td>${branch.branchCity}</td>
							<td>${branch.branchZipcode}</td>
							<%-- <td><a href="/deleteBranch?branchId=${branch.branchId}">Delete</a> --%>
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</c:if>


	${status}

</body>
</html>























