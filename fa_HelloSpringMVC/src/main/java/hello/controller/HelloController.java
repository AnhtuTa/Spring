package hello.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping(value={"/", "index"})
	public String index(Model model) {
		model.addAttribute("message", "today is: " + new Date());
		return "index";	
	}
}
