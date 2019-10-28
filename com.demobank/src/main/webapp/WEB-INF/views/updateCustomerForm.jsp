<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
</script>
<title>Update Form</title>
</head>
<body>
	<form:form action="updateCustomerForm2" method="GET">
		<div >
			<table>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					
					<th>Actions</th>
				</tr>
				<tr>
					<td><input type="hidden" value="${customerId}" name="customerId" />	${customerId} </td>
				
					<td><input type="text" value="${customerName}"
						name="customerName" required="required" /></td>
					<td><input type="text" value="${customerEmail}"
						name="customerEmail" required="required" /></td>
					<td><input type="text" value="${customerPhone}"
						name="customerPhone" maxlength="12" required="required"
						onkeypress="addDashesPhone(this)" /></td>
					
						
						
					<td><button type="submit" value="update">Update</button></td>
					
					
					<td><input type="hidden" value="${customerGender}"
						name="customerGender" required="required" /></td>
					<td><input type="hidden" value="${customerSsn}" name="customerSsn"
						required="required" /></td>
					<td><input type="hidden" value="${customerDob}" name="customerDob"
						required="required" /></td>
					<td><input type="hidden" value="${username}" name="username"
						required="required" /></td>
					<td><input type="hidden" value="${password}" name="password"
						required="required" /></td>
					
				</tr>

			</table>
		</div>

	</form:form>
</body>
</html>



















