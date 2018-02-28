package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Project Spring boot đầu tiên chạy thành công
 * Đéo hiểu sao mấy lần trước chỉ tạo project thôi mà chạy vẫn lỗi!
 */
@SpringBootApplication
public class FuckSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuckSpringBootApplication.class, args);
	}
}

/*
Spring Boot chạy thế nào?

Ứng dụng của bạn được bắt đầu bởi việc thực thi class HelloSpringBootApplication.
Class này được chú thích bởi @SpringBootApplication

Chú thích @SpringBootApplication là tương đương với việc sử dụng 
@Configuration, @EnableAutoConfiguration và @ComponentScan với 
các thuộc tính mặc định của chúng.

Như vậy @SpringBootApplication giúp bạn tự động cấu hình Spring, 
và tự động quét (Scan) toàn bộ project để tìm ra các thành phần 
Spring (Controller, Bean, Service,...)

*/