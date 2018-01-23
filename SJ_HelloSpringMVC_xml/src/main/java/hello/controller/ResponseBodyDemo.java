package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Annotation RequestBody và RestController được sử dụng khi
//muốn trả về giá trị là text (json, xml, text…) thay vì 
//trả về view. Thường áp dụng khi phát triển các API

@Controller
public class ResponseBodyDemo {

	/*
	 * Annotation @ResponseBody được thêm vào trước các method của các controller để chỉ dẫn rằng method này sẽ trả về text thay vì trả về view.
	 * ngoài ra còn có: Annotation @RestController: tương đương với @Controller + @ResponseBody
	 * Annotation @RestController được dùng trước các class, các method trong class này sẽ trả về text thay vì trả về view.
	 */
	@ResponseBody
	@RequestMapping("/test31")
	public String test31() {
		StringBuilder builder = new StringBuilder();
		builder.append("<title>Test31</title>")
			   .append("<h2>This is test31 method</h2>")
			   .append("<h2>Hello world</h2>");
		return builder.toString();
	}
	
}
