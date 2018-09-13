package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;

@Controller
@Api(tags = "This is base controller")
public class BaseController {

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
