<%@page session="false"%>
<%@page isELIgnored="false" %>
<html>
<head>
<title>${title}</title>
</head>
<body>
	<jsp:include page="_menu.jsp" />
	<h1>User info : ${userInfo}</h1>
</body>
</html>