package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping(value="/test-xss", method=RequestMethod.POST)
	public String testXSS(@RequestParam("enter_something") String something, Model model) {
		model.addAttribute("enter_something", something);
		return "text_xss";
	}
}
