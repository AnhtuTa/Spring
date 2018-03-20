package hello.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value= {"/{locale:en|vi}/", "/", "/{locale:en|vi}/home"})
	public String home(Model model, HttpServletRequest request) {
		model.addAttribute("message", "Today I overslept! My mom said: \"Try and get an early night so I don't oversleep tomorrow\"");
		return "index";
	}
	
	@RequestMapping(value = "/user_info", method = RequestMethod.GET)
	public String userInfoWithoutLocale(HttpServletRequest req) {
		String ls = (String) req.getSession().getAttribute("localeString");
		if(ls == null) ls = "en";
		return "redirect:/" + ls + "/user_info";
	}

	@RequestMapping(value = "/{locale:en|vi}/user_info", method = RequestMethod.GET)
	public String userInfo(Principal principal) {
		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();
		System.out.println("[/user_info] User Name: " + userName);
		return "userInfo";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDeniedWithoutLocale(HttpServletRequest req) {
		String ls = (String) req.getSession().getAttribute("localeString");
		if(ls == null) ls = "en";
		return "redirect:/" + ls + "/403";
	}
	
	@RequestMapping(value = "/{locale:en|vi}/403", method = RequestMethod.GET)
	public String accessDenied() {
		return "403Page";
	}
	
	@RequestMapping(value = "/english")
	public String switchToEnglish(HttpServletRequest request) {
		//get previous URL
		String referer = request.getHeader("Referer");
		int index = referer.indexOf("/vi/");
		if(index > 0) {
			return "redirect:/en/" + referer.substring(index + 4);
		} else {
			return "redirect:" + referer;	//vẫn ở lại trang đó
		}
	}
	
	@RequestMapping(value = "/vietnamese")
	public String switchToVietnamese(HttpServletRequest request) {
		//get previous URL
		String referer = request.getHeader("Referer");
		int index = referer.indexOf("/en/");
		if(index > 0) {
			return "redirect:/vi/" + referer.substring(index + 4);
		} else {
			return "redirect:" + referer;	//vẫn ở lại trang đó
		}
	}
}
