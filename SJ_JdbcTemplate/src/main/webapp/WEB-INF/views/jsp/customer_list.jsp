<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>customer_list</title>
	<style>
	table {
		border-collapse: collapse;
		margin-top: 20px;
	}
	
	td, th {
		padding: 10px;
	}
	</style>
</head>

<c:url value="/add-customer" var="addCustomer" />
<c:url value="/view-customer" var="urlView" />
<c:url value="/update-customer" var="urlUpdate" />
<c:url value="/delete-customer" var="urlDelete" />
	
<body>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h1>List Customer</h1>
	<c:if test="${empty customerList}">
		<h3>There's no customer in databases</h3>
	</c:if>
	<c:if test="${not empty customerList}">
		<table border="1">
			<tr>
				<th>Name</th>
				<th>Address</th>
				<th>View</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="customer" items="${customerList}">
				<tr style="border: 1px black solid">
					<td>${customer.name}</td>
					<td>${customer.address}</td>
					<td><a href="${urlView}/${customer.id}">View</a></td>
					<td><a href="${urlUpdate}/${customer.id}/${customer.name}/${customer.address}">Edit</a></td>
					<td><a href="${urlDelete}/${customer.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>