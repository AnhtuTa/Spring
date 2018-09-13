package hello.listener;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class MyListener2 {
	@JmsListener(destination = "des2")
	public void receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			
			// In ra console data được gửi tới
			System.out.println("[MyListener2] Received message:");
			@SuppressWarnings("unchecked")
			Map<Object, Object> map = new Gson().fromJson(messageData, Map.class);
			for(Object key : map.keySet()) {
				System.out.println(map.get(key).toString());
			}
		} else {
			System.out.println(jsonMessage.toString());
		}
	}

}
