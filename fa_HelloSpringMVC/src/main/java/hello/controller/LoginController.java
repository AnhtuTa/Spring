package hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;

import hello.model.Fresher;
import hello.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	//Spring ver2.0:
//	@RequestMapping(value="/login")
//	public ModelAndView showLogin() {
//		ModelAndView mav = new ModelAndView("login");	//tham số là tên file view ta muốn render
//		mav.addObject("fresher_demo", new Fresher("Anhtu", "1234"));
//		
//		return mav;
//	}
	
	//Spring ver3.0:
	@RequestMapping(value="/login")
	public String showLogin(Model model) {
		model.addAttribute("fresher_demo", new Fresher());
		
		return "login";		//return tên file view
	}
	
	@RequestMapping(value="/dashboard")
	//f1 (fresher_demo) được load từ trang login
	public String login(@ModelAttribute("fresher_demo") Fresher f1, Model model) {
		if(loginService.checkLogin(f1)) {
			model.addAttribute("fresher_demo", f1);
			model.addAttribute("loginedUser", f1.getUsername());
			return "dashboard";
		} else {
			model.addAttribute("error", true);
			model.addAttribute("f_demo", new Fresher("phanh", "lee"));
			model.addAttribute("info", "This is a message");
			return "redirect:login";
		}
	}
}
