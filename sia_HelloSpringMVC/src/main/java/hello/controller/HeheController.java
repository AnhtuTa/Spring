package hello.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeheController {
	@RequestMapping(value="/show/{something}")
	@ResponseBody
	public String show(@PathVariable(value="something") String st) {
		return "You said: <b>" + st + "</b>";
	}
	
	@RequestMapping(value= {"/show"})
	public String show_empty() {
		return "show";
	}
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request, HttpServletResponse response, Model model) {
		request.setAttribute("message", "This is a demo about HttpServletRequest, "
				+ "HttpServletResponse in controller");
		return "test";
	}
}
