package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("message", "Winter is over! Now is Spring! I hate Spring!");
		return "index";
	}
	
	@RequestMapping(value = { "/testJsp" }, method = RequestMethod.GET)
	public String testJspView(Model model) {
		model.addAttribute("message", "Winter is over! Now is Spring! I hate Spring!");
		return "testJsp";
	}

	@RequestMapping(value = { "/testThymeleaf" }, method = RequestMethod.GET)
	public String testThymeleafView(Model model) {
		model.addAttribute("message", "Today is bad day, really bad!!!");
		return "th_page1";
	}
}