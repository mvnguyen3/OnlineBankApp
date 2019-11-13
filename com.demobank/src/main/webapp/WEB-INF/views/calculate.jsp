<%@page import="java.util.Map"%>
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
<title>Calculate</title>
<style>
body {
	background-color: whitesmoke;
	max-width: 700px;
	margin: auto;
}
</style>
</head>
<body>
	<div class="form-group">
		<form action="calculate" method="get">
			<table>
				<tr>
					<th align="center" colspan="4">Factorial</th>
				</tr>
				<tr>
					<td>x</td>
					<td><input type="number" name="x" required="required" /></td>


				</tr>
				<!-- <tr>
					<td>y</td>
					<td><input type="text" name="y" /></td>

				</tr>
 -->
				<tr>
					<td><button class="btn btn-primary" type="submit" value="save">Calculate</button></td>
				</tr>

			</table>
		</form>
	</div>
	<table>

		<thead>
			<tr>
				<th>Input</th>
				<th>Result</th>
			</tr>
		</thead>
		<tbody>

			<%
				try {
					Map<Integer, String> map = (Map) session.getAttribute("myMaps");
					for (Integer key : map.keySet()) {
			%>



			<tr>
				<td><%=key%></td>

				<td><%=map.get(key)%></td>
			</tr>
			<%
				}
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			%>


		</tbody>
	</table>

</body>
</html>









