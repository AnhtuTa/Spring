package demo.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	@RequestMapping(value= {"/admin"})
	public String home(Model model) {
		boolean isHasPermission = false;
		@SuppressWarnings("unchecked")
		Collection<SimpleGrantedAuthority> authorities = 
				(Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities();
		Iterator<SimpleGrantedAuthority> iter = authorities.iterator();
		while(iter.hasNext()) {
			SimpleGrantedAuthority auth = iter.next();
			if(auth.getAuthority().equals("ROLE_ADMIN")) isHasPermission = true;
		}
		System.out.println("isHasPermission = " + isHasPermission);
		
		return "admin_index";
	}
}
