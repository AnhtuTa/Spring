package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Chú ý: Spring Boot mặc định sẽ tự động cấu hình Spring JDBC, 
 * và tạo ra các Spring BEAN liên quan tới Spring JDBC, 
 * các tự động cấu hình này của Spring Boot bao gồm:
	DataSourceAutoConfiguration
	DataSourceTransactionManagerAutoConfiguration
 */
@SpringBootApplication
public class O7planBootJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(O7planBootJdbcApplication.class, args);
	}
}
