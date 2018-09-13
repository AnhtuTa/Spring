package hello.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hello.dao.MessageDAO;
import hello.entity.Message;
import hello.model.ChatMessage;
import hello.utils.TimeFormat;

@RestController
public class MessageRESTController {

	@Autowired
	private MessageDAO messageDAO;

	@Deprecated
	@RequestMapping(value = { "/rest/messages/{roomId}/{start}/{amount}" }, 
		method = RequestMethod.GET)
	public List<Message> getMessages(@PathVariable("roomId") long roomId, //
			@PathVariable("start") int start, @PathVariable("amount") int amount) {
		return messageDAO.getMessages(roomId, start, amount);
	}
	
	@RequestMapping(value = { "/rest/chat-messages/{roomId}/{start}/{amount}" }, 
		method = RequestMethod.GET)
	public List<ChatMessage> getChatMessages(@PathVariable("roomId") long roomId, //
			@PathVariable("start") int start, @PathVariable("amount") int amount) {
		return getChatMessagesFromMessages(
				messageDAO.getMessages(roomId, start, amount));
	}
	
	@RequestMapping(value = { "/rest/chat-older-messages-than/{roomId}/{fromMessageId}/{amount}" }, 
		method = RequestMethod.GET)
	public List<ChatMessage> getOlderChatMessagesThan(@PathVariable("roomId") long roomId, //
			@PathVariable("fromMessageId") long fromMessageId, @PathVariable("amount") int amount) {
		return getChatMessagesFromMessages(
				messageDAO.getOlderMessagesThanMessage(roomId, fromMessageId, amount));
	}
	
	private List<ChatMessage> getChatMessagesFromMessages(List<Message> msgList) {
		List<ChatMessage> chatMsgList = new ArrayList<>();
		for(Message msg : msgList) {
			chatMsgList.add(new ChatMessage(msg.getId(), msg.getUser().getId(), msg.getUser().getFullname(), //
					msg.getContent(), TimeFormat.formatTime(msg.getTime()), //
					ChatMessage.MessageType.CHAT, msg.getContentType()));
		}
		
		return chatMsgList;
	}
}
