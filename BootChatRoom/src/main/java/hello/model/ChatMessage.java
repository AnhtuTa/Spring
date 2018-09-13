package hello.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class này có các field trùng với format của data gửi từ client,
 * tức là trùng với file ChatMessage.js,
 * do đó nếu sửa thì phải sửa cả ở cả file ChatMessage.js
 * @author tu.ta1
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
	private long messageId;
	private long senderId;
	private String senderName;
	private String content;
	private String time;
	private MessageType type;
	private int contentType;	//1 = text message, 2 = image, 3 = sticker, 4 = gif

	/*** enum ***/
	public enum MessageType {
		CHAT, JOIN, LEAVE, ENTER, PHOTO
	}
	
	/**
	 * Nếu để tên hàm này là "getInfo" thì Java sẽ hiểu class này
	 * có thêm 1 thuộc tính là "info", do đó messagingTemplate sẽ gửi cả nội
	 * dung của hàm này đi (bên WebSocketController)
	 */
	public String retrieveInfo() {
		return senderId + " - " + senderName + " - " + content + " - " + type;
	}
}
