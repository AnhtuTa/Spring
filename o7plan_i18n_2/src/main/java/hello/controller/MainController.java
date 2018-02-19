package hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.service.UserService;

@Controller
public class MainController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/{locale:en|ja|vi}/login")
    public String login(Model model) {
        return "login";
    }
	
	@RequestMapping(value = "/{locale:en|ja|vi}/login_result")
    public String loginResult(Model model, @RequestParam("username") String username,
    		@RequestParam("password") String password) {
		if(userService.checkLogin(username, password)) {
			model.addAttribute("username", username);
			return "login_result";
		}
        else return "redirect:login";
    }
}
