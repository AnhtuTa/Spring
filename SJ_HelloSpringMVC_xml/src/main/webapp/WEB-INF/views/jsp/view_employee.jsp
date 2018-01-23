<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	td {padding: 5px;}
</style>
</head>
<body>
	<jsp:include page="_menu.jsp"></jsp:include>
	<table border="1" style="border-collapse: collapse">
		<tr>
			<td>Id:</td>
			<td>${employee.id }</td>
		</tr>
		<tr>
			<td>Name:</td>
			<td>${employee.name }</td>
		</tr>
		<tr>
			<td>Address:</td>
			<td>${employee.address }</td>
		</tr>
		<tr>
			<td>Email:</td>
			<td>${employee.email }</td>
		</tr>
		<tr>
			<td>Gender:</td>
			<td>${employee.gender }</td>
		</tr>
		<tr>
			<td>Favorite:</td>
			<td><c:if test="${not empty employee.favorites}">
					<c:forEach var="favorite" items="${employee.favorites}">${favorite}<br></c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>Position:</td>
			<td>${employee.position}</td>
		</tr>
	</table>
</body>
</html>