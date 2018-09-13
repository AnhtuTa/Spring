<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
	<title>home</title>
</head>
<body>
	<h2>Spring boot demo build with Gradle!</h2>
	<div>Message: ${message}</div>
	
	<h3>EL demo</h3>
	<div>My singleID: ${mySingleID}</div>
	
	<h3>JSTL demo</h3>
	<ul>
		<c:forEach items="${names}" var="name">
			<li>${name}</li>	
		</c:forEach>
	</ul>
</body>
</body>
</html>