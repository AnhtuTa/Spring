package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FuckController {
	@RequestMapping(value="/")
	@ResponseBody
	public String home() {
		StringBuilder sb =  new StringBuilder();
		sb.append("<h2>Fuck Spring boot</h2>");
		sb.append("<h3>Hiện tại thì Spring boot 1.5.10.RELEASE ko bị lỗi phần JPA nữa</h2>");
		sb.append("<div>Lần trước lỗi là do file .jar ko valid</div>");
		return sb.toString();
	}
}
