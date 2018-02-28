<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<ul class="st_ul">
	<c:forEach items="${stList}" var="st">
		<li class="st_wrapper">
			<div>ID: ${st.id}</div>
			<div>Name: ${st.name}</div>
			<div>Address: ${st.address}</div>
		</li>
	</c:forEach>
</ul>