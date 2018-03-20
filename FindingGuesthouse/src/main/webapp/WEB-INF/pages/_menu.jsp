<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<c:set var="ls" value="${sessionScope.localeString}" />
<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">
	<a href="${pageContext.request.contextPath}/${ls}/home">Home</a> | &nbsp;
	<a href="${pageContext.request.contextPath}/${ls}/user_info">User Info</a> | &nbsp; 
	<a href="${pageContext.request.contextPath}/${ls}/admin">Admin</a> | &nbsp;

	<c:if test="${pageContext.request.userPrincipal.name != null}">
    	<a href="${pageContext.request.contextPath}/logout">Logout</a> | &nbsp;
	</c:if>

	<a href="/english">English</a> | &nbsp;
	<a href="/vietnamese">Tiếng Việt</a> | &nbsp;
	
</div>