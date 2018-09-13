package hello.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@Autowired
	private Environment env;
	
	@RequestMapping("/home")
	public String home(Model model) {
		List<String> names = new ArrayList<>();
		names.add("Anhtu");
		names.add("Toan");
		names.add("Huy");
		names.add("Nguyen Bka");
		
		model.addAttribute("names", names);
		model.addAttribute("mySingleID", "tu.ta1");
		model.addAttribute("message", env.getProperty("message.hello"));
		
		return "home";
	}
}
