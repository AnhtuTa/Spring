package hello.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController {
	
	@RequestMapping("/login")
	public String loginWithoutLocale(HttpServletRequest req) {
		String error = req.getParameter("error");
		System.out.println("error = " + error);
		String ls = (String) req.getSession().getAttribute("localeString");
		if(ls == null) ls = "en";
		
		if(error == null) return "redirect:/" + ls + "/login";
		else return "redirect:/" + ls + "/login?error=" + error; 
	}
	
	@RequestMapping(value = "/{locale:en|vi}/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/logout_successful", method = RequestMethod.GET)
	public String logoutSuccessfulWithoutLocale(HttpServletRequest req) {
		String ls = (String) req.getSession().getAttribute("localeString");
		if(ls == null) ls = "en";
		return "redirect:/" + ls +"/logout_successful";
	}

	@RequestMapping(value = "/{locale:en|vi}/logout_successful", method = RequestMethod.GET)
	public String logoutSuccessful() {
		return "logoutSuccessful";
	}
}
