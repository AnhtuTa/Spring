<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
	<title>Chat đê!</title>
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
	
	<h3 style="text-align: center;">Spring WebSocket Chat Demo</h3>
	<a href="${pageContext.request.contextPath}/logout">Logout</a>
	
	<h3>Select a conversation to start chatting</h3>
	<c:if test="${userRoomSet.size() == 0}">
		<div>You don't have any conversation!</div>
		<div>Here are some public conversation you can join</div>
		<ul>
			<c:forEach items="${demoRoom}" var="room">
				<li><a href="${pageContext.request.contextPath}/messages/${room.id}">${room.name}</a></li>
			</c:forEach>
		</ul>
	</c:if>
	
	<ul>
		<c:forEach items="${userRoomSet}" var="room">
			<li><a href="${pageContext.request.contextPath}/messages/${room.id}">${room.name}</a></li>
		</c:forEach>
	</ul>
</body>
</body>
</html>