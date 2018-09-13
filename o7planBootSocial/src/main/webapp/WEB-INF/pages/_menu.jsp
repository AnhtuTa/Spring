<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>

<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">
	<a href="${pageContext.request.contextPath}/home">Home</a> | &nbsp;
	<a href="${pageContext.request.contextPath}/user_info">User Info</a> | &nbsp; 
	<a href="${pageContext.request.contextPath}/admin">Admin</a> | &nbsp;

	<c:if test="${pageContext.request.userPrincipal.name != null}">
    	<a href="${pageContext.request.contextPath}/logout">Logout</a> | &nbsp;
	</c:if>
</div>