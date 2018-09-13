<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="conversation-list-section">
	<h3>Welcome <span class="fullname">${fullname}</span>!!!</h3>
	<!-- <div class="div-error">
		CHÚ Ý: APP ĐANG TRONG THỜI GIAN BẢO TRÌ :v :). VUI LÒNG SỬ DỤNG KNOX
	</div> -->
	<div class="div-info" id="loading_too_long_info">
		Nếu page load lâu, có thể bị lỗi. Vui lòng tải lại trang!
	</div>
	<div class="div-info">
		csrf token = ${_csrf.token}<br>
		Please don't share this token to someone elses, or they could impersonate you to send a message!
	</div>
	
	<div style="display:none;" id="request_noti_message" class="div-warning"></div>
	
	<h3 style="margin-top: 10px;">Your conversations:</h3>
	<div>
		<ul>
			<c:forEach items="${userRoomSet}" var="room">
				<li><a href="${pageContext.request.contextPath}/messages/${room.id}">${room.name}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
<script type="text/javascript">
<!--
	setTimeout(function() {
		// document.getElementById("loading_too_long_info").style.display = 'none';
		$("#loading_too_long_info").hide(300);
	}, 5000);
//-->
</script>