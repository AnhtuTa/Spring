package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	@RequestMapping(value = { "/", "/index" })
	public String index_wtf() {
		return "index";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}
}
/*
 * Class HelloController được đánh dấu là @Controller và nằm trong package được
 * scan nên dispatcherServlet khi nhận request sẽ kiểm tra những request sẽ được
 * xử lý bảo HelloController
 * 
 * - method index() + Giá trị maping là “/” tức là khi gửi một request tới url
 * “/” nó sẽ được xử lý bởi method index() + return “index”: nó sẽ kiểm tra xem
 * các file view map trong spring là gì và trả về, trong ví dụ này nó sẽ trả về
 * file index.jsp trong folder WEB-INF/views/jsp + Trong @RequestMapping không
 * chỉ rõ request method là Get, Post, Put… nên nó sẽ chấp nhận tất cả các
 * request method tới.
 * 
 * - method hello() + Giá trị maping là “/hello” tức là khi gửi một request tới
 * url “/hello” nó sẽ được xử lý bởi method hello() + return “hello”: nó sẽ kiểm
 * tra xem các file view map trong spring là gì và trả về, trong ví dụ này nó sẽ
 * trả về file hello.jsp trong folder WEB-INF/views/jsp + Trong @RequestMapping
 * chỉ rõ method = Get nên chỉ có request = Get mới được xử lý bởi method này
 * 
 */