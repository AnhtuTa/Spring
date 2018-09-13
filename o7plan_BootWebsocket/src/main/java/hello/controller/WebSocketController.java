package hello.controller;

import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import hello.model.ChatMessage;

@Controller
public class WebSocketController {
	/*
	 * tất cả các tin nhắn được gửi từ các máy khách có đích đến bắt 
	 * đầu bằng /app sẽ được định tuyến tới các phương thức xử lý 
	 * tin nhắn được chú thích bằng @MessageMapping
	 */
	@MessageMapping("/chat.sendMessage")	//người dùng gửi tin nhắn từ /app/chat.sendMessage
	@SendTo("/topic/publicChatRoom")	//server gửi lại những người dùng subscribe /topic/publicChatRoom
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		System.out.println("[WebSocketController.sendMessage] chatMessage = " + //
				chatMessage.getContent());
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/publicChatRoom")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, //
			SimpMessageHeaderAccessor headerAccessor) {

		System.out.println("[WebSocketController.addUser] chatMessage = " + //
				chatMessage.getContent());

		// Add username in web socket session.
		// Để khi người dùng disconnect thì lôi giá trị này ra thông báo cho những người khác
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		
		Map<String, Object> sessionMap = headerAccessor.getSessionAttributes();
		for(String key : sessionMap.keySet()) {
			System.out.println(key + ": " + sessionMap.get(key));
		}
		return chatMessage;
	}

}