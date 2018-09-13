package hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import hello.interceptor.HttpHandshakeInterceptor;

/*
 * SockJS:
 * Không phải tất cả các trình duyệt đều hỗ trợ giao thức WebSocket. 
 * Vì vậy SockJS là một tùy chọn dự phòng (fallback option), nó sẽ 
 * được kích hoạt cho các trình duyệt không hỗ trợ WebSocket. 
 * SockJS chỉ đơn giản là một thư viện JavaScript.
 * 
 * Ví dụ này kích hoạt một Message broker trong bộ nhớ đơn giản. 
 * Nhưng bạn được tự do sử dụng bất kỳ nhà môi giới thư đầy đủ 
 * tính năng nào khác như RabbitMQ hoặc ActiveMQ.
 */
@Configuration
@EnableWebSocketMessageBroker // enable WebSocket Server
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Autowired
	private HttpHandshakeInterceptor handshakeInterceptor;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// đăng ký một websocket endpoint mà các máy khách sẽ sử dụng để 
		// kết nối với máy chủ websocket này
		// SockJS được sử dụng để bật tùy chọn dự phòng cho các trình duyệt
		// không hỗ trợ websocket
		// STOMP là viết tắt của Simple Text Oriented Messaging Protocol. 
		// Nó là một giao thức nhắn tin xác định định dạng và quy tắc trao 
		// đổi dữ liệu.
		// Tại sao chúng ta cần STOMP? Vâng, WebSocket chỉ là một giao thức
		// truyền thông. Nó không xác định những thứ như - Cách gửi thư chỉ 
		// cho những người dùng đã đăng ký một chủ đề cụ thể hoặc cách gửi
		// thư đến một người dùng cụ thể. Chúng ta cần STOMP cho các chức năng này.
		// (có từ Stomp trong tên của method này :v)
		registry.addEndpoint("/ws").withSockJS().setInterceptors(handshakeInterceptor);
	}

	/*
	 * cấu hình Message broker sẽ được sử dụng để định tuyến thư 
	 * từ một khách hàng này đến ứng dụng khách khác
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// các thư có đích bắt đầu bằng “/app” sẽ được định tuyến đến 
		// các phương thức xử lý tin nhắn
		registry.setApplicationDestinationPrefixes("/app");
		
		// các thông điệp có đích bắt đầu bằng “/topic” nên được định 
		// tuyến tới Message broker. Message broker sẽ
		// phát các tin nhắn đến tất cả các khách hàng được kết nối 
		// đã đăng ký một chủ đề cụ thể
		registry.enableSimpleBroker("/topic");
	}
}
