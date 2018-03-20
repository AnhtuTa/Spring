package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MeterController {
	@RequestMapping(value = "/error")
	public String pageNotFound() {
//		String ls = (String) req.getSession().getAttribute("localeString");
//		if (ls == null) ls = "en";
		return "404Page";
	}
}
