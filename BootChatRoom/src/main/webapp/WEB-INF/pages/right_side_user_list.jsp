<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="participant-list-section">
	<h3 style="margin-top: 10px;text-align: center;">Participants:</h3>
	<div>
		<ul>
			<c:forEach items="${participants}" var="participant">
				<li>${participant}</li>
			</c:forEach>
		</ul>
	</div>
</div>
