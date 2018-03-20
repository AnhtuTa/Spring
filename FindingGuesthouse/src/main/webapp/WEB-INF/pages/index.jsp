<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>home</title>
</head>
<body>
	<jsp:include page="_menu.jsp" />
	<h2>Hello world!</h2>
	<h3>Welcome to finding guesthouse service system!</h3>
	<div>Message from controller: ${message}</div>
	<div>Message from i18n: <spring:message code="msg.hello_world" /> </div>
</body>
</html>