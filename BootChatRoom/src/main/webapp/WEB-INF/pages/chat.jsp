<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<noscript>
    <meta http-equiv="refresh" content="0; url=/noJS" />
    <style type="text/css">div {display: none;}</style>
</noscript>
<head>
	<title>${roomName}</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.png">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/model_images.css" />
	
	<!-- https://cdnjs.com/libraries/sockjs-client -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
	<!-- https://cdnjs.com/libraries/stomp.js/ -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script src="https://cdn.rawgit.com/mervick/emojionearea/master/dist/emojionearea.min.js"></script>
	<link rel="stylesheet" href="https://cdn.rawgit.com/mervick/emojionearea/master/dist/emojionearea.min.css" />
	
	<!-- 2 thẻ meta sau để phục vụ cho AJAX (vì gửi request thì cần có csrf token) -->
	<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<style type="text/css">		
		.btn_upload_file {
			position: absolute;
		    left: 13px;
		    border: 1px solid #b1b1b1;
		    width: 40px;
		    height: 40px;
		    border-radius: 3px;
		    background: url(${pageContext.request.contextPath}/img/icon_photo.png) no-repeat center;
		    background-size: 75%;
		    cursor: pointer;
		}
		.btn_upload_file2, .btn_sticker, .btn_gif, .btn_like {
			position: absolute;
			background-repeat: no-repeat;
		    background-size: 34px 726px;
		    height: 32px;
		    width: 32px;
		    display: inline-block;
		    cursor: pointer;
		    transition: opacity .3s ease-in-out;	/*Chỉ cần dùng: 0.3s cũng được */
		    z-index: 5;
		}
		.btn_upload_file2 {
			left: 89px;
		    top: 3px;
		    background-image: url(${pageContext.request.contextPath}/img/icon_sprite.png);
		    background-position: 0 -272px;
		    opacity: .7;
		}
		.btn_upload_file2:hover {
			opacity: 1;
		}
		.emojionearea-wrapper:after {
			left: 5px !important;
		}
		.btn_sticker {
			left: 27px;
		    top: 3px;
			background-image: url(${pageContext.request.contextPath}/img/icon_sprite.png);
		    background-position: 0 -340px;
		    opacity: .2;
		}
		.btn_sticker:hover {
			opacity: .4;
		}
		.btn_gif {
			left: 58px;
		    top: 3px;
			background-image: url(${pageContext.request.contextPath}/img/icon_sprite.png);
		    background-position: 0 -102px;
		    opacity: .7;
		}
		.btn_gif:hover {
			opacity: 1;
		}
		.btn_like {
			left: 117px;
		    top: 3px;
		    background-image: url(/chat/img/icon_sprite.png);
		    background-position: 0 -204px;
		    opacity: .7;
		}
		.btn_like:hover {
			opacity: 1;
		}
		.btn-send {
			background-image: url(${pageContext.request.contextPath}/img/icon_like.png);
		}
	</style>
</head>
<body onload="document.messageFormName.msg_input.focus()">
	<jsp:include page="left_side_conversation_list.jsp" />

	<div id="chat-container">
		<div class="chat-header">
			<input class="room_name" value="${roomName}" readonly
				ondblclick="editRoomName(this)"
				onkeypress="pressKeyOnRoomName(this, event)" />
			<div id="tooltip_edit_room_name" style="display: none;">Edit
				room's name. Press Enter to save!</div>
			<a href="${pageContext.request.contextPath}/logout">Logout</a>
		</div>

		<hr style="margin: 0;" />

		<div class="msg-area-wrapper" id="messageAreaWrapper">
			<div id="connecting">Connecting...</div>
			<div id="showOlderMessages" class="btn btn-show-older-msg"
				style="display: none;">Show older messages</div>
			<div id="loader1" style="text-align: center;display:none;">
				<div class="loader"></div>
				<span class="loader_text">Getting older messages...</span>
			</div>
			
			<!-- Cấm không được có ký tự nào trong thẻ ul, kể cả backspace! -->
			<ul id="messageArea"></ul>
			
			<div id="loader2" style="text-align: center;display:none;margin: 7px 0;">
				<div class="loader"></div>
				<span class="loader_text">Sending...</span>
			</div>
		</div>

		<form id="messageForm" name="messageFormName" action=""
			enctype="multipart/form-data" method="post">
			<div class="input-message">
				<!-- <div id="images_wrapper"></div> -->
				<!-- Button upload ảnh sẽ được tạo sau khi trang load xong
				<div class="btn_upload_file" type="button" onclick="chooseFile();"></div> -->
				<div id="select_file_wrapper">
					<input style="display: none;" type="file" name="file_upload"
						id="input_select_file" accept="image/*" />
				</div>

				<!--  <input type="text" id="message" name="msg_input"
					class="message_input" autocomplete="off"
					placeholder="Type a message..." /> -->
				<textarea id="message" name="msg_input" style="display:none" onkeypress="onInputTextArea"
					class="message_input" autocomplete="off"
					placeholder="Type a message..."></textarea>
				<button type="submit" id="btnSend" class="btn btn-send">Send</button>
			</div>
			<div style="clear:both;"></div>
		</form>

		<!-- The Modal -->
		<div id="myModal" class="modal">
			<span class="close-model">&times;</span>
			<img class="modal-content"id="img01">
		</div>
	</div>

	<jsp:include page="right_side_user_list.jsp" />

	<script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";
		var userId = ${userId};
		var fullname = "${fullname}";
		var roomId = ${roomId};
		var isNewbie = "${isNewbie}";
		var roomName = "${roomName}";
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ChatMessage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/chat.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/sticker_gif.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/create_emoji.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/create_sticker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/create_gif.js"></script>
</body>
</body>
</html>