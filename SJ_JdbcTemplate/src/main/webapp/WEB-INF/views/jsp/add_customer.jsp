<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>customer_list</title>
	<style>
		input {
			padding: 5px;
		}
		input[type=submit] {
			margin-top: 10px;
			padding: 7px;
			background: #5495ff;
			color: #fff;
			font-weight: bold;
			font-size: 17px;
			border: 0;
		}
		td {padding: 3px;}
	</style>
</head>

<c:url value="/add-customer" var="addCustomer" />

<body>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h1>Add new Customer</h1>
	<form action="${addCustomer}" method="post">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><input type="text" name="address"></td>
			</tr>
		</table>
		<input type="submit" value="Save" />
	</form>
</body>
</html>