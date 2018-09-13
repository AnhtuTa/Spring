<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>Register</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.png">
	<link rel='stylesheet' href="${pageContext.request.contextPath}/css/style.css">
	<!-- <link rel='stylesheet' href="${pageContext.request.contextPath}/css/login.css"> -->
	<link rel='stylesheet' href="${pageContext.request.contextPath}/css/register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body onload="document.f.username.focus()">
<div class="body_wrapper">
	<div id="login-container">
		<h1 class="h1_register">Register</h1>
		<c:if test="${requestScope.DUPLICATE_USER_OR_EMAIL != null}">
			<div class="my_error">This username has been used!</div>
		</c:if>
		<c:if test="${requestScope.UNKNOWN_ERROR != null}">
			<div class="my_error">Unknown error</div>
		</c:if>
		<!-- CHÚ Ý: thẻ form:form sẽ tự động thêm csrf token! -->
		<form:form name="f" action="${pageContext.request.contextPath}/register" id="loginForm"
			method="POST" modelAttribute="user" cssClass="form_register">
			<form:input path="username" placeholder="Username" class="input_username"/><br>
			<form:errors path="username" cssClass="form_error" element="div"/>
			
			<div style="position: relative;">
				<form:password path="password" placeholder="Password" class="input_username"/><br>
				<i class="fa fa-question-circle singleid-hint" aria-hidden="true" style="font-size: 20px;"
						title="Quên pass thì liên hệ tu.ta1 để lấy lại nhé :v"></i>
			</div>
			<form:errors path="password" cssClass="form_error" element="div"/>
			
			<form:password path="confirmPassword" placeholder="Confirm password" class="input_username"/><br>
			<c:if test="${requestScope.PASSWORD_DOESNT_MATCH != null}">
				<div class="my_error">Password doesn't match</div>
			</c:if>
			
			<form:input path="fullname" placeholder="Fullname" class="input_username"/><br>
			<form:errors path="fullname" cssClass="form_error" element="div"/>
			
			<div style="position: relative;">
				<form:input path="singleId" placeholder="Single ID" class="input_username"/>
				<i class="fa fa-question-circle singleid-hint" aria-hidden="true" style="font-size: 20px;"
					title="Single ID for reset password.
In case you forgot your password, we can use this ID to send you 
a link to reset it through Know Message :v"></i><br>
			</div>
			
			<input name="submit" class="btn btn_submit" type="submit" value="Register" />
		</form:form>
		
		<div class="div-login">
			Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a>
		</div>
	</div>
</div>
</body>
</html>