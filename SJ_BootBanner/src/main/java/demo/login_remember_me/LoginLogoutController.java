package demo.login_remember_me;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginLogoutController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest req, @RequestParam(required=false) String message, Model model) {
		if(req.getQueryString() != null) {
			System.out.println(req.getRequestURI() + "?" + req.getQueryString());
		} else {
			System.out.println(req.getRequestURI());
		}
		
		@SuppressWarnings("unchecked")
		Collection<SimpleGrantedAuthority> authorities = 
				(Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities();
		Iterator<SimpleGrantedAuthority> iter = authorities.iterator();
		while(iter.hasNext()) {
			SimpleGrantedAuthority auth = iter.next();
			System.out.println(auth.getAuthority());
		}
		
		if (message != null && !message.isEmpty()) {
			if (message.equals("max_session")) {
				model.addAttribute("message", "This accout has been login from another device!");
			}
		}

		//Nếu người dùng đã login rồi thì ko được login hoặc register nữa
		//(phải logout trước đã). Do đó ta sẽ redirect về trang chủ
		//CHÚ Ý: ko được return tên file nhé, phải redirect về trang chủ,
		//ko được: return "index";
		if(req.getSession().getAttribute("SPRING_SECURITY_CONTEXT") != null) {
			req.getSession().setAttribute("USER_MUST_LOGOUT_FIRST", 1);
			return "redirect:/home";
		}
		return "login";
	}

	@RequestMapping(value = "/logout_successful", method = RequestMethod.GET)
	public String logoutSuccessful(HttpServletRequest req) {
		return "logoutSuccessful";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		return "logoutSuccessful";
	}
}
