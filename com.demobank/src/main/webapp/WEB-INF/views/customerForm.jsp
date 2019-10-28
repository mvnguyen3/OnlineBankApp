<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	function toggle(f) {
		if (document.getElementById("toggle").innerText == "Nav") {
			document.getElementById("userBtn").style.display = "inline-block";
			document.getElementById("accountBtn").style.display = "inline-block";
			document.getElementById("toggle").innerText = "x";

		} else {
			document.getElementById("userBtn").style.display = "none";
			document.getElementById("accountBtn").style.display = "none";
			document.getElementById("toggle").innerText = "Nav";

		}

	}
</script>
<title>Customer Form</title>
<style>
body {
	background-color: whitesmoke;
	
}

#userBtn{
	display: none; 
	
	
}

#accountBtn{
	display: none; 
	
	
}
#toggle{
	display: inline-block; 
	background: "blue";	 
	
}
</style>
</head>
<body>
	<div class="Navigation-bar">
		<table>
			<thead>

			</thead>
			<tbody>
				<tr>
					<td><a><button onclick="toggle(this)" id="toggle" class="btn btn-primary" >Nav</button></a></td>
					<td><a href="/"><button id="userBtn" class="btn btn-primary">User</button></a></td>
					<td><a href="/#"><button id="accountBtn" class="btn btn-primary">Account</button></a></td>					
				</tr>
			</tbody>
		</table>
	</div>
	<div class="form-group">
		<form:form action="saveCustomer" method="post"
			modelAttribute="customer">

			<table>
				<tr>
					<td>Name</td>
					<td><form:input type="text" name="customerName"
							path="customerName" /></td>
					<td><form:errors cssClass="error" path="customerName" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input type="text" name="customerEmail"
							value="@gmail.com" path="customerEmail" /></td>
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
				<tr>
					<td><button class="btn btn-primary" type="submit" value="save">Submit</button></td>
				</tr>
			</table>
		</form:form>
	</div>


	<c:if test="${not empty customers}">
		<div style="overflow: auto">
			<table border="1">
				<thead>
					<tr>
						<td>Id</td>
						<td>Name</td>
						<td>Email</td>
						<td>Phone</td>
						<td>Gender</td>
						<td>SSN</td>
						<td>DOB</td>
						<td>Action</td>
					<tr>
				</thead>
				<c:forEach items="${customers}" var="customer">
					<tbody>
						<tr>
							<td>${customer.customerId}</td>
							<td>${customer.customerName}</td>
							<td>${customer.customerEmail}</td>
							<td>${customer.customerPhone}</td>
							<td>${customer.customerGender}</td>
							<td>${customer.customerSsn}</td>
							<td>${customer.customerDob}</td>
							<td><a
								href="/updateCustomer?customerId=${customer.customerId}&customerName=${customer.customerName}
							&customerEmail=${customer.customerEmail}
							&customerPhone=${customer.customerPhone}&customerGender=${customer.customerGender}
							&customerSsn=${customer.customerSsn}&customerDob=${customer.customerDob}">Update
							</a> &nbsp; &nbsp; &nbsp; <a
								href="/deleteCustomer?customerId=${customer.customerId}">Delete</a>
							<td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</c:if>
	${status}

</body>
</html>






























