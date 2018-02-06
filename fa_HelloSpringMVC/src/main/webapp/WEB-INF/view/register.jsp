<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<style type="text/css">
		input {padding: 5px;}
	</style>
</head>
<body>
	<div align="center">
		<c:if test="${param['error']}"><div>Error! Username is exist or unknown error!</div></c:if>
		<form:form method="post" action="register_result" modelAttribute="fresher_reg">
			<table>
				<tr>
					<td><form:label path="username">Username</form:label></td>
					<td><form:input path="username" name="username" id="username" required="required"/></td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:input path="password" name="password" id="password" required="required"/></td>
				</tr>
				<tr>
					<td><form:label path="location">Location</form:label></td>
					<td><form:input path="location" name="location" id="location" required="required"/></td>
				</tr>
				<tr>
					<td><form:label path="year">Year</form:label></td>
					<td><form:input path="year" name="year" id="year" required="required"/></td>
				</tr>
				<tr>
					<td></td>
					<td><form:button name="register" id="register">Register</form:button></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>