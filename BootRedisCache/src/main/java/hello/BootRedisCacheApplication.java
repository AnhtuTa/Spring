package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BootRedisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRedisCacheApplication.class, args);
	}
}
