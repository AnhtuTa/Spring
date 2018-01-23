<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h1>Spring MVC internationalization</h1>
	Language :
	<a href="?language=en">English</a>|
	<a href="?language=vi">Tiếng Việt</a>|
	<a href="?language=fr">French</a>|
	<a href="?language=cn">Chinese</a>
	<h2>Hi, this is test51.jsp</h2>

	<h2>
		hello :
		<spring:message code="hello" text="default text" />
	</h2>

	Current Locale : ${pageContext.response.locale}
</body>
</html>