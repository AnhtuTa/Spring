package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hello.service.UserService;

@SpringBootApplication
public class BootChatRoomApplication {
	
	@Autowired
	static UserService ms;
	
	public static void main(String[] args) {
		SpringApplication.run(BootChatRoomApplication.class, args);
	}
}
