<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
	<title>Login</title>
    <link rel="shortcut icon" href="/img/favicon.png">
	<style type="text/css">
	.my_error {
		color: red;
	}
	#loginForm {
		margin-top: 20px;
	}
	.input_username {
		padding: 5px;
	}
	</style>
	<link rel="stylesheet" href="/css/style.css" />
	<link rel="stylesheet" href="/css/chat.css" />
</head>
<body onload="document.loginForm.username.focus()">
	<h1 style="text-align: center;margin-top: 20px;">Login</h1>

	<div id="login-container">
		<h1 class="title">Enter your username</h1>
		<form id="loginForm" name="loginForm" method="POST">
			<input type="text" class="input_username" name="username" />
			<button type="submit" class="btn">Login</button>
		</form>
	</div>
</body>
</body>
</html>