package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Internationliaztion {
	@RequestMapping("/test51")
	public String home() {
		return "test51";
	}
}
