package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticResource {
	@RequestMapping("/test71")
	public String test71() {
		return "test71";
	}
}
