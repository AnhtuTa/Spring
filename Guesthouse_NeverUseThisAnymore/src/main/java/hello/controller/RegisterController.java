package hello.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.dao.MyUserDAO;
import hello.model.MyUser;

@Controller
public class RegisterController {
	@Autowired
	MyUserDAO myUserDao;
	
	@RequestMapping(value= {"/{locale:en|vi}/register", "/register"}, method=RequestMethod.GET)
	public String register(Model model) {
		if(!model.containsAttribute("newRegisterUser")) {
			model.addAttribute("newRegisterUser", new MyUser());
		}
		return "register";
	}
	
//	@RequestMapping(value= {"/{locale:en|vi}/register", "/register"}, method=RequestMethod.POST)
//	public String doRegister(@ModelAttribute("newRegisterUser") @Valid MyUser newRegisterUser,
//			BindingResult result, HttpServletRequest request) {
//		System.out.println("[RegisterController] currentURI = " + request.getSession().getAttribute("currentURI").toString());
//		if(result.hasErrors()) {
//			//return request.getSession().getAttribute("currentURI").toString();
//			//return "/en/register";
//			System.out.println("\n\n\nError occur!\n\n\n");
//		}
//		System.out.println("[RegisterController] result = " + result);
//		
////		if(myUserDao.saveMyUser(newRegisterUser)) return "registerSuccessful";
////		else return "register";
//		return "redirect:" + request.getSession().getAttribute("currentURI").toString();
//	}
	
	@RequestMapping(value= {"/{locale:en|vi}/register", "/register"}, method=RequestMethod.POST)
	public String doRegister(@ModelAttribute("newRegisterUser") @Valid MyUser newRegisterUser,
			BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("\n\n\nError occur!\n\n\n");
			return "/en/register";
		}
		System.out.println("[RegisterController] result = " + result);
		
//		if(myUserDao.saveMyUser(newRegisterUser)) return "registerSuccessful";
//		else return "register";
		return "/home";
	}
	
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String doPostAddUser(@ModelAttribute("user") @Valid MyUser user, BindingResult result) {
		if (result.hasErrors()) {
			return "add_user";
		}
		return "view_user";
	}
}
