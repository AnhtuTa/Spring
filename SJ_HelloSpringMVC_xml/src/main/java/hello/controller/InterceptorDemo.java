package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InterceptorDemo {
	@RequestMapping("test61")
	public String test61() {
		return "test61";
	}
	
	@RequestMapping("spring/test62")
	public String test62() {
		return "test62";
	}
	
	
}
