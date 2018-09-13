<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
	<title>Login</title>
	<style type="text/css">
		.my_error {
			color: red;
		}
	</style>
</head>
<body onload="document.f.username.focus()">
	<jsp:include page="_menu.jsp" />

	<h1>Login</h1>

	<!-- /login?error=true -->
	<c:if test="${param.error == 'true'}">
		<div class="my_error">
			Login Failed!!!<br /> Reason :
			${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
			<%-- Hoặc có thể viết: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} --%>
		</div>
	</c:if>
	<c:if test="${message != ''}">
		<div class="my_error">${message}</div>
	</c:if>

	<form name='f'
		action="${pageContext.request.contextPath}/j_spring_security_check"
		method='POST'>
		<table>
			<tr>
				<td>Username</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
                <td>Remember Me:</td>
                <td><input type="checkbox" name="remember-me" /></td>
            </tr>
			<tr>
				<td></td>
				<td><input name="submit" type="submit" value="Submit" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>