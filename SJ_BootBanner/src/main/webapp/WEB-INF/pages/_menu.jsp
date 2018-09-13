<style>
	.input_logout {
		background: #47ff3b;border: 0;cursor: pointer;
	}
	.input_logout:hover {
		background: #52ed48;
	}
</style>
<a href="${pageContext.request.contextPath}/">Home</a> &nbsp;|&nbsp;
<a href="${pageContext.request.contextPath}/profile">Profile</a> &nbsp;|&nbsp;
<a href="${pageContext.request.contextPath}/admin">Admin</a> &nbsp;|&nbsp;
<a href="${pageContext.request.contextPath}/login">Login</a> &nbsp;|&nbsp;
<form style="display: inline-block;" action="${pageContext.request.contextPath}/logout" method="POST">
	<input class="input_logout" type="submit" value="Logout"/>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>		
</form>