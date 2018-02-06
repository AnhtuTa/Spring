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
		<c:if test="${param['register_success']}"><div>Register success! You can login now!</div></c:if>
		<form:form method="post" action="dashboard" modelAttribute="fresher_demo">
			<table>
				<tr>
					<td><form:label path="username">Username</form:label></td>
					<td><form:input path="username" name="username" id="username" /></td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:input path="password" name="password" id="password" /></td>
				</tr>
				<tr>
					<td></td>
					<td><form:button name="login" id="login">Login</form:button></td>
				</tr>
			</table>
		</form:form>
		<c:if test="${param['error']}">Incorrect username or password</c:if>
		<div>${param['f_demo'].name}</div>	<!-- Ko thể nhận 1 đối tượng kiểu object -->
		<div>${param['f_demo']}</div>
		<div>${param['info']}</div>
		
	</div>
</body>
</html>