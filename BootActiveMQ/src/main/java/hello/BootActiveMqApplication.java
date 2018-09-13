package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/*
 * 
 */
@SpringBootApplication
@EnableJms
public class BootActiveMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootActiveMqApplication.class, args);
	}
}
