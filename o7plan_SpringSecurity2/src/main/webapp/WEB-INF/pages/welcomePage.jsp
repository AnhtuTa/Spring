<%@page session="false"%>
<%@page isELIgnored="false" %>
<html>
<head>
<title>${title}</title>
</head>
<body>
    <jsp:include page="_menu.jsp" />
	<h1>Message : ${message}</h1>
	<div>This is welcomePage.jsp</div>
</body>
</html>