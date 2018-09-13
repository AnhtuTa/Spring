package hello.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.entity.User;
import hello.service.UserService;

@Controller
public class LoginLogoutRegisterController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String showLoginPage(HttpServletRequest request, Principal principal, Model model) {
		//System.out.println("[showLoginPage] " + principal.getName());
		if(principal == null) {
			if(request.getSession().getAttribute("register_success") != null) {
				request.getSession().removeAttribute("register_success");
				model.addAttribute("info_message", "Register successful! Now you can login.");
			}
			return "login";
		} else {
			return "redirect:/messages/123";
		}
	}

	@RequestMapping(path = "/logout")
	public String logout(HttpServletRequest request) {
		request.getSession(true).invalidate();

		return "redirect:/login";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register(HttpServletResponse response, Model model) {
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new User());
		}
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") @Valid User user, BindingResult result,
			HttpServletRequest request) {
		
		// Lỗi do validate input
		if (result.hasErrors()) {
			return "register";
		}
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			request.setAttribute("PASSWORD_DOESNT_MATCH", 1);
			return "register";
		} else if ("OK".equals(userService.saveUser(user))) {
			request.getSession().setAttribute("register_success", "1");
			return "redirect:/login";
		} else if ("duplicate_key".equals(userService.saveUser(user))) {
			request.setAttribute("DUPLICATE_USER_OR_EMAIL", 1);
			return "register";
		} else {
			request.setAttribute("UNKNOWN_ERROR", 1);
			return "register";
		}
	}
	
}
