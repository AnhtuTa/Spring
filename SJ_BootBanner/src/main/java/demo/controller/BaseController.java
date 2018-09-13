package demo.controller;

import java.security.Principal;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaseController {
	@RequestMapping(value= {"/", "/home"})
	public String home(Model model, HttpServletRequest request,
			@RequestParam(required=false) String message) {
		model.addAttribute("message_from_controller", "Hello Spring boot, today is July 18th, 2018");

		if(request.getSession().getAttribute("USER_MUST_LOGOUT_FIRST") != null) {
			model.addAttribute("USER_MUST_LOGOUT_FIRST", 1);
			request.getSession().removeAttribute("USER_MUST_LOGOUT_FIRST");
		}
		
		if (message != null && !message.isEmpty()) {
			if (message.equals("timeout")) {
				model.addAttribute("message", "Time out! Session has been expired!");
			}
		}
		return "index";
	}
	
	@RequestMapping(value="/profile")
	public String profile(Model model, Principal principal, HttpServletRequest request) {
		model.addAttribute("username", principal.getName());
		System.out.println("[profile] ContextPath = " + request.getContextPath());
		return "profile";
	}
	
	@RequestMapping(value = {"/403"}, method = RequestMethod.GET)
	public String accessDenied() {
		return "403Page";
	}
}
