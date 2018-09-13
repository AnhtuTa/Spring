<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
	<title>Login</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.png">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
	<style type="text/css">		
		.input_username {
			background: url(${pageContext.request.contextPath}/img/icon_username20.png) no-repeat scroll 7px 7px;
			background-color: #fff;
		}
		
		.input_password {
			background: url(${pageContext.request.contextPath}/img/icon_password20.png) no-repeat scroll 7px 7px;
			background-color: #fff;
		}
	</style>
</head>
<body onload="document.loginForm.username.focus()">
	
	<div id="login-container">
		<h1 class="title">Login</h1>
		<c:if test="${info_message != null}">
			<div style="margin-top: 7px;color: #2196f3;font-size: 17px;font-weight: bold;">${info_message}</div>
		</c:if>
		<div>Chú ý: Có thể ứng dụng này sẽ không hoạt động trên IE!</div>
		<form id="loginForm" name="loginForm" method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
			<input type="text" class="input_username" name="username" placeholder="Username" /><br>
			<input type="password" class="input_password" name="password" placeholder="Password" /><br>
			<div class="div-rememberme">
				<label class="checkbox-container">Remember me
                    <input type="checkbox" name="remember-me">
                    <span class="checkmark"></span>
                </label>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit" class="btn btn-login">Login</button>
		</form>
		<div class="div-register">
			Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a>
		</div>

		<c:if test="${param.error == 'true'}">
			<div class="my_error" style="margin-top: 5px;">
				<%-- Login Failed!!!<br /> Reason :
				${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message} --%>
				<%-- Hoặc có thể viết: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} --%>
				
				Error! Username or password is incorrect!
			</div>
		</c:if>
		<c:if test="${message != ''}">
			<div class="my_error">${message}</div>
		</c:if>
	</div>
</body>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
</html>