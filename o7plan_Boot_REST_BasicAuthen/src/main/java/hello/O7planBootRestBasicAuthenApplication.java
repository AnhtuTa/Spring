package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Trong bài viết này, tôi sẽ hướng dẫn bạn tạo ra một ứng dụng RESTful Web Service
	và bảo mật nó với Basic Authentication. Điều này có nghĩa là ứng dụng của bạn sẽ 
	cung cấp các nguồn dữ liệu (Resource), nhưng người dùng muốn sử dụng nguồn dữ 
	liệu này họ phải được xác thực (authenticate) với phương thức xác thực cơ bản 
	(Basic Authentication).
	Basic Authentication (Xác thực cơ bản)
	Để truy cập vào các nguồn dữ liệu (Resource) được bảo mật bởi Basic 
	Authentication, người dùng phải gửi một request và trong request đó có chứa 
	thông tin username/password được đính kèm trên Header.
	Bạn có thể sử dụng trình duyệt để truy cập vào một nguồn dữ liệu được bảo mật 
	bởi Basic Authentication, trong trường hợp này một hộp thoại (dialog) sẽ hiển 
	thị cho phép bạn nhập vào username/password, thông tin này sẽ được đính kèm 
	trong request để gửi đến REST Server
	
	Trong ví dụ này chúng ta cần một thư viện để chuyển đổi (convert) XML sang 
	đối tượng Java và ngược lại. Và một thư viện khác để chuyển đổi JSON sang 
	Java và ngược lại.
	
	JSON <==> Java
	spring-boot-starter-web đã tích hợp sẵn thư viện jackson-databind, thư viện 
	này giúp chuyển đổi JSON thành đối tượng Java và ngược lại
	
	XML <==> Java
	Spring Boot sử dụng JAXB (Có sẵn trong JDK) như là một thư viện mặc định 
	để chuyển đổi XML và Java. Tuy nhiên các lớp Java của bạn cần phải được 
	chú thích (annotated) bởi @XmlRootElement,... Vì vậy lời khuyên của tôi là
	bạn nên sử dụng jackson-dataformat-xml như là một thư viện để chuyển đổi 
	XML và Java. Để sử dụng jackson-dataformat-xml bạn cần khai báo nó trong 
	tập tin pom.xml
 */
@SpringBootApplication
public class O7planBootRestBasicAuthenApplication {

	public static void main(String[] args) {
		SpringApplication.run(O7planBootRestBasicAuthenApplication.class, args);
	}
}
