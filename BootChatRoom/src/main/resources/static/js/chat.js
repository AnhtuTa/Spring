'use strict';

const STR_JOIN = 'JOIN';
const STR_CHAT = 'CHAT';
const STR_LEAVE = 'LEAVE';
const STR_ENTER = "ENTER";
const STR_APPEND_TOP = "APPEND_TOP";
const STR_APPEND_BOTTOM = "APPEND_BOTTOM";
const CONTENT_TYPE_TEXT_MESSAGE = 1;
const CONTENT_TYPE_IMAGE = 2;
const CONTENT_TYPE_STICKER = 3;
const CONTENT_TYPE_GIF = 4;
const CURR_URI = window.location.pathname + window.location.search;

var messageForm = document.querySelector('#messageForm');
var messageArea = document.querySelector('#messageArea');
var messageAreaWrapper = document.querySelector('#messageAreaWrapper');
var connectingElement = document.querySelector('#connecting');
var tooltip_edit_room_name = document.getElementById("tooltip_edit_room_name");
var showOlderMessages = document.getElementById("showOlderMessages");
var select_file_wrapper = document.getElementById("select_file_wrapper");
var input_select_file = document.getElementById("input_select_file");
var modalImg = document.getElementById("img01");
var modal = document.getElementById('myModal');
var spanCloseModel = document.getElementsByClassName("close-model")[0];
var loader1 = document.getElementById("loader1");
var loader2 = document.getElementById("loader2");
var messageInput = document.querySelector('#message');

// phải đảm bảo chỉ có 1 thẻ có class là emojionearea-editor
var emojionearea, emojionearea_editor, emojionearea_button, emojionearea_picker, sticker_picker = undefined;
// ko thể gán emojionearea_editor = document.getElementsByClassName("emojionearea-editor")[0];
// vì lúc này trang web chưa load xong nên ko tồn tại thẻ nào có class=emojionearea-editor

var stompClient = null;

// set csrf token for each AJAX request
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e,xhr,options) {
	xhr.setRequestHeader(header, token);
});

// Add events for some elements
messageForm.addEventListener('submit', sendMessage, true);
showOlderMessages.addEventListener('click', getOlderMessages);
input_select_file.addEventListener("change", fire_ajax_submit);
spanCloseModel.onclick = function() { 
    modal.style.display = "none";
}
//When the user clicks anywhere outside of the image modal, close it
window.onclick = function(event) {
	var sticker_picker = document.getElementsByClassName("sticker-picker")[0];
	var btn_sticker = document.getElementsByClassName("btn_sticker")[0];
	
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function connect() {
	var socket = new SockJS(contextPath + '/ws');
	stompClient = Stomp.over(socket);
	stompClient.debug = null;  //disable debug messages on sockjs- STOMP
	stompClient.connect({}, onConnected, onError);
}

// Connect to WebSocket Server.
connect();

function onConnected() {
	// Subscribe to the Public Topic
	// Subscribe 2 kênh: 1 kênh nhận tin nhắn trong room và 1 kênh
	// nhận thông báo từ server
	stompClient.subscribe(contextPath + '/channel/' + roomId, onMessageReceived);
	stompClient.subscribe(contextPath + '/notiyFromServer/' + userId, receiveNotificationFromServer);
	
	var chatMessage;
	
	if(isNewbie === 'true') {
		// Tell to server you joined this room
		chatMessage = new ChatMessage(0, userId, fullname, "", "", STR_JOIN, 1);
		console.log("Welcome you to join this room!");
	} else {
		// you just enter this room (you've already joined this room before!)
		// Chú ý: vẫn phải send message tới server để thông báo rằng tao vừa vào
		// room, để server lưu id của tao vào session socket
		chatMessage = new ChatMessage(0, userId, fullname, "", "", STR_ENTER, 1);
		console.log("Welcome back!");
	}
	stompClient.send(contextPath + "/app/chat/" + roomId + "/enterRoom", {}, chatMessage.toJSONString());

	connectingElement.classList.add('hidden');
	//$("#connecting").hide(500);
	
	getSomeNewestMessages();
}

function onError(error) {
	connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
	connectingElement.style.color = 'red';
}

function getSomeNewestMessages() {
	let xhttp = new XMLHttpRequest();
	let start = 0;
	let amount = 15;
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var json = JSON.parse(this.responseText);
			for(var i=json.length-1; i>=0; i--) {
				appendMessages(json[i], STR_APPEND_BOTTOM, null);
			}

			showOlderMessages.style.display = '';

			askNotificationPermission();
		}
	};
	xhttp.open("GET", contextPath + "/rest/chat-messages/" + roomId + "/" + start + "/" + amount, true);
	xhttp.send();
}

function getOlderMessages() {
	loader1.style.display = '';
	if(messageArea.childNodes.length === 0) {
		setTimeout(function() {
			alert("Nothing more to show!");
			loader1.style.display = 'none';
		}, 10);
		return;
	}

	let xhttp = new XMLHttpRequest();
	let fromMessageId = messageArea.childNodes[0].getAttribute("msgId");
	let amount = 10;
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var messageJSON = JSON.parse(this.responseText);
			if(messageJSON.length === 0) {
				alert("Nothing more to show!");
			} else {
				// for(var i=0; i<json.length; i++) {
				// 	appendMessages(json[i], STR_APPEND_TOP, 0);
				// }

				appendMessages(messageJSON[messageJSON.length-1], STR_APPEND_TOP, 0);
				var appendedMessages = 0;
				for(var i=messageJSON.length-2; i>=0; i--) {
					console.log(messageJSON[i]);
					appendMessages(messageJSON[i], STR_APPEND_TOP, appendedMessages+1);
					appendedMessages++;
				}
			}
			
			loader1.style.display = 'none';
		}
	};
	xhttp.open("GET", contextPath + "/rest/chat-older-messages-than/" + roomId + "/" + fromMessageId + "/" + amount, true);
	xhttp.send();
}

function sendMessage(event) {
	var messageContent = messageInput.value.trim().replace(/"/g, '\\"');
	messageContent = messageContent.replace(/\\/g, '\\\\');
	messageContent = escapeHtml(messageContent);
	messageContent = messageContent.replace(/\r\n|\r|\n/g,"<br>");
	console.log("messageContent:", messageContent);
	
	if (messageContent && stompClient) {
		loader2.style.display = '';
		messageAreaWrapper.scrollTop = messageAreaWrapper.scrollHeight;
		var chatMessage = new ChatMessage(0, userId, fullname, messageContent, "", STR_CHAT, 1);
		// có vẻ như stompClient tự động send cả csrf token rồi!
		stompClient.send(contextPath + "/app/chat/" + roomId + "/sendMessage", {}, chatMessage.toJSONString());
		messageInput.value = '';
		
		if(emojionearea_editor === undefined) {
			emojionearea_editor = document.getElementsByClassName("emojionearea-editor")[0];
		}
		emojionearea_editor.innerHTML = '';
	}
	event.preventDefault();
}

function onInputTextArea(event) {
	console.log("keycode: ", event.keyCode);
}

function onMessageReceived(payload) {
	var message = JSON.parse(payload.body);
	appendMessages(message, STR_APPEND_BOTTOM, null);
	loader2.style.display = 'none';
    
	if(Notification.permission == 'granted' && message.senderId != userId) {
		showNotification(message);
	}
}

function askNotificationPermission() {

	Notification.requestPermission().then(function(result) {
		if (result === 'denied') {
			document.getElementById("request_noti_message").innerHTML = 
				"Chú ý: Bạn đã tắt thông báo nên sẽ <b>KHÔNG</b> nhận được thông báo khi có tin nhắn mới. " + 
				"Chúng tôi khuyến cáo bạn nên bật thông báo để có thể reply nhanh nhất!";
			$("#request_noti_message").show(300);
			return;
		}
		if (result === 'default') {
			// console.log('The permission request was dismissed.');
			return;
		}
		
		// Do something with the granted permission.
		// alert("Thông báo được cho phép, bạn sẽ nhận được thông báo khi có tin nhắn mới :)");
	});

}

function showNotification(message) {
	var notiBody;
	if(message.contentType === CONTENT_TYPE_TEXT_MESSAGE) {
		if(message.content.length > 40)
			notiBody = message.content.substring(0, 40) + "...";
		else notiBody = message.content;
	} else if(message.contentType === CONTENT_TYPE_IMAGE) {
		notiBody = "Send an image";
	} else if(message.contentType === CONTENT_TYPE_STICKER) {
		notiBody = "Send a sticker";
	} else if(message.contentType === CONTENT_TYPE_GIF) {
		notiBody = "Send a gif";
	}
	
    var notify = new Notification(
        message.senderName + " (" + roomName + ")",		// title
        {
            body: notiBody,
            icon: contextPath + '/img/favicon.png',
            tag: CURR_URI	// The ID of the notification
        }
    );
    notify.onclick = function() {
        // window.location.href = this.tag;    //
    	window.open(CURR_URI);
    	this.close();
    }
    
    // setTimeout(notify.close.bind(notify), 4000);
}
// position (String): quy định việc append trước hay sau
// elementIndex (int): append message trước messageArea.chileNodes[elementIndex]
/* để cho đơn giản thì hàm này chỉ thực hiện:
- Nếu append bottom: append vào cuối của messageArea, khi đó ko cần tham số element
- Nếu apppend top: append vào phía trước của element, khi đó cần tham số element */
function appendMessages(message, position, elementIndex) {
	var messageItemWrapper = document.createElement('li');
	var messageContent = document.createElement('div');
	messageContent.classList.add("message-content");
	
	var messageTime = document.createElement('div');
	if(message.time === '') {
		messageTime.innerHTML = getCurrentTime();
	} else {
		messageTime.innerHTML = message.time;
	}

	if (message.type === STR_JOIN) {
		// khi người dùng vào room lần đầu
		messageItemWrapper.classList.add('event-message');
		messageContent.classList.add("someone-join");
		message.content = message.senderName + ' joined!';
		console.log(message);
	} else if (message.type === STR_LEAVE) {
		// khi người dùng rời khỏi room
		messageItemWrapper.classList.add('event-message');
		messageContent.classList.add("someone-leave");
		message.content = message.senderName + ' left!';
		console.log(message);
	} else {
		var needToShowUsername;
		
		if(position === STR_APPEND_TOP) {
			needToShowUsername = checkIfNeedToShowUsername(message.senderId, elementIndex-1);
		} else if(position === STR_APPEND_BOTTOM) {
			needToShowUsername = checkIfNeedToShowUsername(message.senderId, messageArea.childNodes.length-1);
		}

		messageItemWrapper.classList.add('chat-message');
		messageItemWrapper.setAttribute("msgId", message.messageId);
		
		if(needToShowUsername) {
			var usernameElement = document.createElement('div');
			usernameElement.classList.add('nickname');
			usernameElement.setAttribute("sender_id", message.senderId);
			usernameElement.innerHTML = message.senderName;
			messageItemWrapper.appendChild(usernameElement);
		}
		
		if(message.senderId == userId) {
			messageItemWrapper.classList.add('your-message-item');
			if(needToShowUsername) usernameElement.classList.add('your-name');
			if(message.contentType === 1) messageContent.classList.add('your-message-content');
			messageTime.classList.add('your-message-time');
		} else {
			messageItemWrapper.classList.add('friend-message-item');
			if(needToShowUsername) usernameElement.classList.add('friend-name');
			if(message.contentType === 1) messageContent.classList.add('friend-message-content');
			messageTime.classList.add('friend-message-time');
		}
	}
	
	if(message.contentType === 1) { // tin nhắn dạng text
		//var textMessage = document.createTextNode(message.content);
		//messageContent.appendChild(textMessage);
		
		// Đã escapeHtml ở phần sendMessage rồi, nên sẽ ko escapeHtml ở đây nữa,
		// vì message.content có thể có thẻ <br>
		var finalMessage;
		if(message.senderId == userId) {
			finalMessage = replacePlainURLsWithLinks(message.content, "color: #fff;text-decoration: underline;");
		} else {
			finalMessage = replacePlainURLsWithLinks(message.content, "text-decoration: underline;");
		}
		finalMessage = emojione.shortnameToImage(finalMessage);
		messageContent.innerHTML = "<div>" + finalMessage + "</div>";
	} else if(message.contentType > 1 && message.contentType < 6) { // tin nhắn dạng hình ảnh
		var imgMessage = document.createElement("img");
		// Note that the order of your code does matter. The onload 
		// function has to be defined before the src of the image is set
		if(position === STR_APPEND_BOTTOM) {
			imgMessage.onload = function() {
			    // the image is ready
				messageAreaWrapper.scrollTop = messageAreaWrapper.scrollHeight;
			};
		}
		if(message.content.startsWith("http"))
			imgMessage.src = message.content;
		else
			imgMessage.src = contextPath + message.content;
		imgMessage.classList.add('img-message');
		if(message.contentType === 2) {
			imgMessage.onclick = function() {
			    modal.style.display = "block";
			    modalImg.src = this.src;
			}
		}
		if(message.contentType === 5) {
			imgMessage.classList.add('like-sticker');
		}
		
		messageContent.appendChild(imgMessage);
	}
	
	messageContent.appendChild(messageTime);
	messageItemWrapper.appendChild(messageContent);
	
	if(position === STR_APPEND_TOP) {
		messageArea.insertBefore(messageItemWrapper, messageArea.childNodes[elementIndex]);
	} else if(position === STR_APPEND_BOTTOM) {
		messageArea.appendChild(messageItemWrapper);
		
		//scroll to the bottom to see newest message
		messageAreaWrapper.scrollTop = messageAreaWrapper.scrollHeight;	
	}
}

// checkPosition: vị trí của phần tử trong mảng messageArea.childNodes
function checkIfNeedToShowUsername(sender_id, checkPosition) {
	if(checkPosition < 0) return true;

	var liTags = messageArea.childNodes;
	
	if(liTags.length >= 1) {
		if(!liTags[checkPosition].classList.contains("event-message")) {
			for(var i = checkPosition; i >= 0; i--) {
				if(liTags[i].nodeType == 1) {	// element node, đề phòng trường hợp gặp text node
					var divNickname = liTags[i].getElementsByClassName("nickname")[0];
					if(divNickname != null) {
						if(divNickname.getAttribute("sender_id") == sender_id) {
							return false;
						}
						break;
					}
				}
			}
		}
	}
	
	return true;
}

function receiveNotificationFromServer(payload) {
	var message = JSON.parse(payload.body);
	alert(message.content);
	console.log("%c" + message.content, "color: red; font-size:17px; font-family: Calibri");
}

function editRoomName(element) {
	element.readOnly = '';
	element.setAttribute("style", "border: 1px solid #2196f3;");
	// tooltip_edit_room_name.style.display = "";
	$("#tooltip_edit_room_name").show(300);
}

// không hiểu tại sao Javascript biết tham số event là KeyboardEvent!
function pressKeyOnRoomName(element, event) {
	var x = event.which || event.keyCode;
	if(x === 13) {	// phím enter
		// Làm ngược lại so với hàm trên
		element.readOnly = 'true';
		element.setAttribute("style", "");
		// tooltip_edit_room_name.style.display = "none";
		$("#tooltip_edit_room_name").hide(300);
		
		setTimeout(function() {
			alert("Chức năng đổi tên phòng hiện tại chưa hoàn thành :v")
		}, 350);
	}
}

function chooseFile() {
	$("#input_select_file").trigger('click');
}

function fire_ajax_submit() {
    $("#btnSend").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: contextPath + "/rest/upload/",
        data: new FormData($('#messageForm')[0]),
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: uploadFileSuccess,
        error: uploadFileFail
    });
}

function uploadFileSuccess(data) {
	var json = JSON.parse(data);
	console.log("json = ", json);
	
    $("#btnSend").prop("disabled", false);
    
    // tạo mới thẻ input (đề phòng trường hợp data của nó là file ảnh vẫn còn,
    // khi gửi tin nhắn lại gửi theo cả data này, nên sẽ dư thừa)
    select_file_wrapper.innerHTML = "";
    select_file_wrapper.innerHTML = '<input style="display: none;" type="file" name="file_upload"' +
	'id="input_select_file" accept="image/*"/>';
    
    // cần set lại sự kiện cho thẻ input
    document.getElementById("input_select_file").addEventListener("change", fire_ajax_submit);
    
    // send message for other members in this room
    if (stompClient) {
		var chatMessage = new ChatMessage(0, userId, fullname, json.image_url, "", STR_CHAT, 2);
		// có vẻ như stompClient tự động send cả csrf token rồi!
		stompClient.send(contextPath + "/app/chat/" + roomId + "/sendMessage", {}, chatMessage.toJSONString());
	}
}

function uploadFileFail(error) {
    console.log("ERROR : ", error);
    if(error.responseJSON.exception != null) {
    	if(error.responseJSON.exception == "org.springframework.web.multipart.MultipartException" && error.responseJSON.message.includes("FileSizeLimitExceededException")) {
        	alert(STR_ERR_FILE_SIZE_LIMIT);
        }
    }
    $("#btnSend").prop("disabled", false);
}

/*
Vào URL: https://localhost:8443/messages/123
Thử nhập ở console:
var cm = new ChatMessage(0, 3, "hehehe haha", "Demo msg from console", "", STR_CHAT, 1);
stompClient.send(contextPath + "/app/chat/123/sendMessage", {}, cm.toJSONString());
*/