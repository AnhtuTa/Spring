<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
	<title>Chat đê!</title>
    <link rel="shortcut icon" href="/img/favicon.png">
	<style type="text/css">
	
	</style>
	<link rel="stylesheet" href="css/style.css" />
	<link rel="stylesheet" href="css/chat.css" />
	
	<!-- https://cdnjs.com/libraries/sockjs-client -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
	<!-- https://cdnjs.com/libraries/stomp.js/ -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<noscript>
    <meta http-equiv="refresh" content="0; url=/noJS" />
    <style type="text/css">div {display: none;}</style>
</noscript>
<body onload="document.messageForm.msg_input.focus()">
	
	<h3 style="text-align: center;">Spring WebSocket Chat Demo</h3>
	<div id="chat-container">
		<div class="chat-header">
			<div class="user-container">
				<span id="username">${username}</span>
				<a href="/logout">Logout</a>
			</div>
		</div>

		<hr />

		<div id="connecting">Connecting...</div>
		<ul id="messageArea">
		</ul>
		<form id="messageForm" name="messageForm">
			<div class="input-message">
				<input type="text" id="message" name="msg_input" class="message_input" autocomplete="off" placeholder="Type a message..." />
				<button type="submit" class="btn btn-send">Send</button>
			</div>
		</form>
	</div>

	<script type="text/javascript" src="/js/chat.js"></script>
<script type="text/javascript">
	
</script>
</body>
</body>
</html>