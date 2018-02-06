package hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework. stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.model.Fresher;
import hello.service.RegisterService;

@Controller
public class RegisterController {
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("fresher_reg", new Fresher());
		return "register";
	}
	
	@RequestMapping(value="/register_result")
	public String login(@ModelAttribute("fresher_reg") Fresher f1, Model model) {
		if(registerService.checkRegister(f1)) {
			model.addAttribute("register_success", true);
			return "redirect:login";
		}
		else {
			model.addAttribute("error", true);
			return "register";
		}
	}
}
