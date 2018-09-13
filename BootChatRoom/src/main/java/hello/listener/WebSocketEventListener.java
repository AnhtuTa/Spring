package hello.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/*
 * lắng nghe việc kết nối socket và việc ngắt kết nối
 * Class này không quan trọng, chỉ phục vụ debug!
 */
@Component
public class WebSocketEventListener {
	/*
	 * Sự kiện này xảy ra khi socket bắt đầu kết nối với server
	 * (ngay sau khi http được nâng cấp lên websocket)
	 */
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		System.out.println("[WebSocketEventListener] Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		System.out.println("[WebSocketEventListener] A web socket disconnected");
	}

}
