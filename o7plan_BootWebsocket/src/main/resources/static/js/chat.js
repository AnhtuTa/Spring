'use strict';

var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('#connecting');

var stompClient = null;
var username = null;

function connect() {
	username = document.querySelector('#username').innerText.trim();
	// MessageBroker là một chương trình trung gian, nó tiếp nhận các
	// tin nhắn được gửi đến trước khi phân phát tới các địa chỉ cần thiết.
	
	// endpoint /ws:
	// MessageBroker phơi bầy ra một endpoint (Điểm cuối) để client 
	// có thể liên lạc và hình thành một kết nối. Để liên lạc client
	// sử dụng thư viện SockJS để làm việc này
	// (Nghĩa là client kết nối với MessageBroker (server) thông qua 1 endpoint)
	// (xem thêm ảnh ở thư mục /src)
	
	// Không phải tất cả các trình duyệt đều hỗ trợ giao thức WebSocket. 
	// Vì vậy SockJS là một tùy chọn dự phòng (fallback option), nó sẽ 
	// được kích hoạt cho các trình duyệt không hỗ trợ WebSocket. SockJS 
	// chỉ đơn giản là một thư viện JavaScript
	var socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, onConnected, onError);
}

// Connect to WebSocket Server.
connect();

function onConnected() {
	// Subscribe to the Public Topic
	stompClient.subscribe('/topic/publicChatRoom', onMessageReceived);

	// Tell your username to the server
	stompClient.send("/app/chat.addUser", {}, JSON.stringify({
		sender : username,
		type : 'JOIN'
	}))

	connectingElement.classList.add('hidden');
}

function onError(error) {
	connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
	connectingElement.style.color = 'red';
}

function sendMessage(event) {
	var messageContent = messageInput.value.trim();
	if (messageContent && stompClient) {
		var chatMessage = {
			sender : username,
			content : messageInput.value,
			type : 'CHAT'
		};
		stompClient.send("/app/chat.sendMessage", {}, JSON
				.stringify(chatMessage));
		messageInput.value = '';
	}
	event.preventDefault();
}

function onMessageReceived(payload) {
	var message = JSON.parse(payload.body);
	// console.log(message);

	var messageItemWrapper = document.createElement('li');
	var messageContent = document.createElement('div');
	messageContent.classList.add("message-content");

	if (message.type === 'JOIN') {
		messageItemWrapper.classList.add('event-message');
		messageContent.classList.add("someone-join");
		message.content = message.sender + ' joined!';
	} else if (message.type === 'LEAVE') {
		// khi người dùng đóng tab chat hoặc refresh trang thì sẽ xảy ra
		// trường hợp này. Việc send message khi người dùng leave được 
		// thực hiện bên WebSocketEventListener.java
		messageItemWrapper.classList.add('event-message');
		messageContent.classList.add("someone-leave");
		message.content = message.sender + ' left!';
	} else {
		var dontNeedToShowUsername = false;
		var liTags = messageArea.childNodes;

		if(!liTags[liTags.length - 1].classList.contains("event-message")) {
			for(var i = liTags.length - 1; i >= 0; i--) {
				if(liTags[i].nodeType == 1) {	// element node, đề phòng trường hợp gặp text node
					var divNickname = liTags[i].getElementsByClassName("nickname")[0];
					if(divNickname != null) {
						if(divNickname.innerHTML == message.sender) {
							dontNeedToShowUsername = true;
						}
						break;
					}
				}
			}
		}
		
		messageItemWrapper.classList.add('chat-message');
		
		if(!dontNeedToShowUsername) {
			var usernameElement = document.createElement('div');
			usernameElement.classList.add('nickname');
			var usernameText = document.createTextNode(message.sender);
			usernameElement.appendChild(usernameText);
			messageItemWrapper.appendChild(usernameElement);
		}
		
		if(message.sender == username) {
			messageItemWrapper.classList.add('your-message-item');
			if(!dontNeedToShowUsername) usernameElement.classList.add('your-name');
			messageContent.classList.add('your-message-content');
		} else {
			messageItemWrapper.classList.add('friend-message-item');
			if(!dontNeedToShowUsername) usernameElement.classList.add('friend-name');
			messageContent.classList.add('friend-message-content');
		}
	}
	
	var messageText = document.createTextNode(message.content);
	messageContent.appendChild(messageText);

	messageItemWrapper.appendChild(messageContent);

	messageArea.appendChild(messageItemWrapper);
	
	//scroll to the bottom to see newest message
	messageArea.scrollTop = messageArea.scrollHeight;
}

messageForm.addEventListener('submit', sendMessage, true);