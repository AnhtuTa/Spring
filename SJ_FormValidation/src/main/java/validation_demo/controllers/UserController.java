package validation_demo.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import validation_demo.entities.User;

/*
 * Lưu ý: Trong method của controller, BindingResult phải nằm 
 * ngay sau đối tượng được validate.
 * Bạn có thể dùng annotation @Valid (javax.validation.valid) hoặc
 * @Validate (org.springframework.validation.annotation.validate) đều được.
 */
@Controller
public class UserController {
	@RequestMapping(value = "/add-user", method = RequestMethod.GET)
	public String doGetAddUser(Model model) {
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new User());
		}
		return "add_user";
	}

	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String doPostAddUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "add_user";
		}
		return "view_user";
	}

}
