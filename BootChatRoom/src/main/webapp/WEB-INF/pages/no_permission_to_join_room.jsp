<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
	<title>${titlePage}</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.png">
	<style type="text/css">
	</style>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>

<noscript>
    <meta http-equiv="refresh" content="0; url=/noJS" />
    <style type="text/css">div {display: none;}</style>
</noscript>
<body>
	<h3 style="text-align: center;">${message}</h3>
	<c:if test="${action == 'join'}">
		<a href="${pageContext.request.contextPath}/join/${roomId}">Join now!</a>
	</c:if>
	<c:if test="${action == 'request_join'}">
		<a href="${pageContext.request.contextPath}/request-join/${roomId}">Request join</a>
	</c:if>
	<c:if test="${action == 'no_permission'}">
		<a href="${pageContext.request.contextPath}/">Return home</a>
	</c:if>
	
</body>
</body>
</html>