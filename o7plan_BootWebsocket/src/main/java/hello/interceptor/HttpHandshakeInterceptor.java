package hello.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/*
 * HTTP Handshake:
 * Cơ sở hạ tầng hiện có gây ra các hạn chế cho việc triển khai 
 * WebSocket, thông thường HTTP đã sử dụng cổng 80 & 443, vì vậy 
 * WebSocket phải sử dụng các cổng khác, trong khi đó hầu hết các 
 * Firewall (Tường lửa) chặn các cổng khác 80 & 443, sử dụng các 
 * Proxy (Ủy quyền) cũng có nhiều vấn đề xẩy ra. Vì vậy để có thể 
 * dễ dàng triển khai, WebSocket sử dụng HTTP Handshake (Cái bắt 
 * tay với HTTP) để nâng cấp. Điều đó có nghĩa là trong lần đầu 
 * tiên client gửi một yêu cầu dựa trên HTTP tới server, nói với 
 * server rằng đó không phải là HTTP, hãy nâng cấp lên WebSocket, 
 * và như vậy chúng hình thành một kết nối. 
 * 
 * Lớp HttpHandshakeInterceptor được sử dụng để xử lý các sự kiện 
 * ngay trước và sau khi WebSocket bắt tay với HTTP. Bạn có thể 
 * làm gì đó trong lớp này.
 * 
 * sự kiện trước và sau khi handshake xảy ra xong thì Http sẽ được
 * nâng cấp lên WebSocket, và kể từ lúc này thì mọi việc chat của 
 * client tới server đều thông qua socket này, do đó ta không thể
 * sử dụng các đối tượng của HTTP như: HttpServletRequest, HttpSession...
 */
@Component
public class HttpHandshakeInterceptor implements HandshakeInterceptor {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HttpHandshakeInterceptor.class);

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, //
			WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		//logger.info("Call beforeHandshake");
		System.out.println("[HttpHandshakeInterceptor] Call beforeHandshake");

		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession();
			attributes.put("sessionId", session.getId());
		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, //
			WebSocketHandler wsHandler, Exception exception) {
		//logger.info("Call afterHandshake");
		System.out.println("[HttpHandshakeInterceptor] Call afterHandshake");
	}

}
