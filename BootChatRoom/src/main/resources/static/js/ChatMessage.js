function ChatMessage(messageId, senderId, senderName, content, time, type, contentType) {
	this.messageId = messageId;
	this.senderId = senderId;
	this.senderName = senderName;
	this.content = content;
	this.time = time;
	this.type = type;
	this.contentType = contentType;
	
	this.toJSONString = function() {
		let kq = `{"senderId": ${this.senderId}, "senderName": "${this.senderName}", ` +
			`"content": "${this.content}", "time": "${this.time}", "type": "${this.type}", ` +
			`"contentType": ${this.contentType}}`;
		return kq;
	}
}