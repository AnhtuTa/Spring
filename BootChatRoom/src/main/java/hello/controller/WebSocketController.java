package hello.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import hello.dao.MessageDAO;
import hello.entity.Message;
import hello.model.ChatMessage;

/*
 * Chú ý: không thể dùng HttpServletRequest, HttpSession ở đây, vì
 * ở đây là WebSocket
 */
@Controller
public class WebSocketController {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@Autowired
	private MessageDAO messageDAO;

	@Autowired
    private ServletContext servletContext;
	/**
	 * gửi tin nhắn từ /chat/roomId/sendMessage
	 * Tại sao viết là: /app/chat/{roomId}/sendMessage lại không được?
	 */
	@MessageMapping("/chat/{roomId}/sendMessage")
	public void sendMessage(@DestinationVariable("roomId") String roomIdString, 
			@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		long roomId;
		try {
			roomId = Long.valueOf(roomIdString);
		} catch (NumberFormatException e) {
			// nhập sai id của room thì đéo gửi tin nhắn tới nó!
			return;
		}
		System.out.println("[WebSocketController.sendMessage] chatMessage = " + //
				chatMessage.getContent());
		
		long userIdOfThisSocket = (long) headerAccessor.getSessionAttributes().get("userId");
		long roomIdOfThisSocket = (long) headerAccessor.getSessionAttributes().get("roomId");
		if(userIdOfThisSocket != chatMessage.getSenderId() || roomIdOfThisSocket != roomId) {
			//send message to user to tell he/she could not send this message!
			ChatMessage cm = new ChatMessage(0, 5, "System", "What the f*ck are you doing???", //
					"", ChatMessage.MessageType.CHAT, 1);
			messagingTemplate.convertAndSend(servletContext.getContextPath() + 
					"/notiyFromServer/" + userIdOfThisSocket, cm);
			return;
		}
		
		// save message to database
		long insertedId = messageDAO.insertMessage(new Message(chatMessage.getContent(), //
				chatMessage.getContentType()), //
				chatMessage.getSenderId(), roomId);
		chatMessage.setMessageId(insertedId);
		
		// server gửi lại những người dùng subscribe /channel/roomId
		messagingTemplate.convertAndSend(servletContext.getContextPath() + 
				"/channel/" + roomId, chatMessage);
	}

	/**
	 * Có 1 người vào room có id = roomId. Có 2 trường hợp xảy ra:
	 * 1. Người này lần đầu tiên tham gia room: ChatMessage.MessageType.JOIN
	 * 2. Người này chỉ vào room: ChatMessage.MessageType.ENTER
	 */
	@MessageMapping("/chat/{roomId}/enterRoom")
	public void enterRoom(@DestinationVariable("roomId") long roomId, //
			@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		// thêm 1 số thuộc tính cho session của socket để phân biệt giữa các socket
		headerAccessor.getSessionAttributes().put("userId", chatMessage.getSenderId());
		headerAccessor.getSessionAttributes().put("fullname", chatMessage.getSenderName());
		headerAccessor.getSessionAttributes().put("roomId", roomId);
		
		// THông báo cho mọi người có 1 thằng mới join vào!
		if(chatMessage.getType().equals(ChatMessage.MessageType.JOIN)) {
			chatMessage.setSenderId(5);	//5 là ID của user 'system' trong database
			chatMessage.setSenderName("");
			
			long insertedId = messageDAO.insertMessage(new Message(chatMessage.getContent(), //
					chatMessage.getContentType()), //
					chatMessage.getSenderId(), roomId);

			chatMessage.setMessageId(insertedId);
			messagingTemplate.convertAndSend(servletContext.getContextPath() + 
					"/channel/" + roomId, chatMessage);
		}
	}

}