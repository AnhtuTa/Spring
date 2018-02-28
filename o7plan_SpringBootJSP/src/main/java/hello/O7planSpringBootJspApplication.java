package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Spring là một framework nổi tiếng vì nó hỗ trợ nhiều công nghệ cho 
 * tầng View (View Layer). Các công nghệ mà Spring hỗ trợ cho tầng View 
 * là JSP, Thymeleaf, Freemarker,... 
 * 
 * Vì sự đơn giản của Thymeleaf, nó được coi như công nghệ mặc định để 
 * sử dụng cho tầng View, và được Spring Boot tự động cấu hình. Vì vậy 
 * nếu bạn lựa chọn JSP cho tầng View, bạn cần phải cấu hình nó.
 */
@SpringBootApplication
public class O7planSpringBootJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(O7planSpringBootJspApplication.class, args);
	}
}
