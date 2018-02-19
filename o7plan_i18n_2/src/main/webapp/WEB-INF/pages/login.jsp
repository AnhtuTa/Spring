<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- CHÚ Ý: Không được để dòng sau:
<%@ page session="false"%> --%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="label.title" /></title>
</head>
<body>
	<div style="text-align: right; padding: 5px; margin: 5px 0px; background: #ccc;">
		<a href="${pageContext.request.contextPath}/en/login">Login
			(English)</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/ja/login">Login
			(Japanese)</a> &nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/vi/login">Login
			(Vietnamese)</a>
	</div>

	<%-- <form method="post" action="login_result"> --%>
	<form method="post" action="${pageContext.request.contextPath}/${sessionScope.LANGUAGE_NAME}/login_result">
		<table>
			<tr>
				<td><strong> <spring:message code="label.userName" />
				</strong></td>
				<td><input name="username" /></td>
			</tr>
			<tr>
				<td><strong> <spring:message code="label.password" />
				</strong></td>
				<td><input name="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><spring:message code="label.submit"
						var="labelSubmit"></spring:message> <input type="submit"
					value="${labelSubmit}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>