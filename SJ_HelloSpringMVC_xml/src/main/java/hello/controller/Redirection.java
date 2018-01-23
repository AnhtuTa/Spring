package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
	- method redirect() không trả về view mà thực hiện redirect sang 
	url “/test43” bằng cách thêm từ “redirect” trước kết quả trả về.
	- cũng có thể thực hiện redirect bằng cách return new RedirectView("test43"); 
	thay vì "redirect:test43";
 */
@Controller
public class Redirection {

	@RequestMapping("/test41")
	public String page1() {
		return "test41";
	}

	@RequestMapping("/test42")
	public String redirect() {
		return "redirect:test43";
		//return new RedirectView("test43");
	}

	@RequestMapping("/test43")
	public String page2() {
		return "test43";
	}
}
