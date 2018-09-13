package hello.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import hello.model.ChatMessage;

/*
 * lắng nghe việc kết nối socket và việc ngắt kết nối 
 */
@Component
public class WebSocketEventListener {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	/*
	 * Chúng ta đã phát sự kiện người dùng tham gia trong phương thức addUser() 
	 * được xác định bên trong WebSocketController. Vì vậy, chúng ta không cần 
	 * phải làm bất cứ điều gì trong sự kiện SessionConnected.
	 * Do đó method sau có thể bỏ đi!
	 */
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		//logger.info("Received a new web socket connection");
		System.out.println("[WebSocketEventListener] Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		String username = (String) headerAccessor.getSessionAttributes().get("username");

		if (username != null) {
			//logger.info("User Disconnected : " + username);
			System.out.println("[WebSocketEventListener] User Disconnected : " + username);

			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setType(ChatMessage.MessageType.LEAVE);
			chatMessage.setSender(username);

			messagingTemplate.convertAndSend("/topic/publicChatRoom", chatMessage);
		}
	}

}
