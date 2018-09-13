package hello.listener;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/*
 * @JmsListener:It marks a method to be the target of a JMS 
 * messagelistener on the specified destination. In our case
 * the destination is destination_demo.This class is responsible 
 * to listen messsage from the destination_demo and process the same.
 * 
 * @SendTo annotation will take care of sending the return 
 * value of receiveMessage() to the destination defined in @SendTo.
 */
@Component
public class MyListener {
	@JmsListener(destination = "destination_demo")
	@SendTo("outbound.queue")
	public String receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("[MyListener] Received message " + jsonMessage);
		String response = null;
		
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			
			// In ra console data được gửi tới
			@SuppressWarnings("unchecked")
			Map<Object, Object> map = new Gson().fromJson(messageData, Map.class);
			for(Object key : map.keySet()) {
				System.out.println(map.get(key).toString());
			}
			response  = "Hello " + map.get("name");
		}
		return response;
	}

}
