<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring boot demo</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.png">
<style>
	.error {color: red;}
	
	.tooltip {
	    position: relative;
	}
	
	.tooltip .tooltiptext {
	    visibility: hidden;
	    width: 120px;
	    background-color: black;
	    color: #fff;
	    text-align: center;
	    border-radius: 6px;
	    padding: 5px 0;
	
	    /* Position the tooltip */
	    position: absolute;
	    z-index: 1;
	    
	    top: -24px;
    	left: 39px;
    	
    	transition: 0.5s;
	}
	
	/* Bottom Arrow */
	.tooltip .tooltiptext::after {
	    content: " ";
	    position: absolute;
	    top: 100%; /* At the bottom of the tooltip */
	    left: 50%;
	    margin-left: -5px;
	    border-width: 5px;
	    border-style: solid;
	    border-color: black transparent transparent transparent;
	}
</style>
</head>
<body>
	<jsp:include page="_menu.jsp" />
	<h2>Spring boot demo</h2>
	<c:if test="${not empty USER_MUST_LOGOUT_FIRST}">
		<div class="error">You must logout first in order to login again</div>
	</c:if>
	<c:if test="${message != ''}">
		<div style="color: blue">${message}</div>
	</c:if>
	<div>Message from controller: ${message_from_controller}</div>
	<div>csrf token = ${_csrf.token}</div>

	<div class="tooltip">
		<span id="tooltip_text" class="tooltiptext">Copied!</span>
	</div>
	<!-- it doesn't work on mobile Safari. In those cases you can use: 
		onClick="this.setSelectionRange(0, this.value.length)" -->
	<textarea onClick="this.select(); copyToClipboard();" style="width: 200px;height: 70px;margin: 10px 0;"
		id="copy_in_js" >Demo about copy to clipboard using Javascript. Click here!</textarea>
	
</body>
<script type="text/javascript">
	function copyToClipboard() {
		document.execCommand('copy');
		var tooltip_text = document.getElementById("tooltip_text");
		tooltip_text.style.visibility = "visible";
		setTimeout(function() {
			tooltip_text.style.visibility = "hidden";
		}, 1000);
	}
</script>
</html>