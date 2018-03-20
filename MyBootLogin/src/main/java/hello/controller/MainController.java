package hello.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.util.WebUtils;

@Controller
public class MainController {
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is welcome page!");
		return "welcomePage";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model, HttpServletRequest request) {
		model.addAttribute("userFullName", request.getSession().getAttribute("userFullName"));
		return "adminPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {
		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();
		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String name = principal.getName();
		System.out.println("name = " + name);
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		return "userInfoPage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal, HttpServletRequest request) {
		if (principal != null) {
			String message = "Hi " + request.getSession().getAttribute("userFullName")
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);
		}

		return "403Page";
	}

	/*
	 * https://kipalog.com/posts/Huong-dan-lap-trinh-Spring-Security
	 * 
	 * SecurityContext là interface cốt lõi của Spring Security, lưu trữ tất cả 
	 * các chi tiết liên quan đến bảo mật trong ứng dụng. Khi chúng ta kích hoạt 
	 * Spring Security trong ứng dụng thì SecurityContext cũng sẽ được kích hoạt theo.
	 * 
	 * Chúng ta sẽ không truy cập trực tiếp vào SecurityContext, thay vào đó 
	 * sẽ sử dụng lớp SecurityContextHolder. Lớp này lưu trữ security context 
	 * hiện tại của ứng dụng, bao gồm chi tiết của principal đang tương tác với 
	 * ứng dụng. Spring Security sẽ dùng một đối tượng Authentication để biểu 
	 * diễn thông tin này. Đoạn code dưới đây sẽ giúp chúng ta lấy được username 
	 * của principal đã được xác thực (username ở đây ta nên hiểu là username 
	 * trong cặp username - password mà người dùng nhập vào khi đăng nhập).
	 * Đoạn code này có thể đặt ở bất kỳ đâu trong ứng dụng:
	 */
	@RequestMapping("/demo")
	@ResponseBody
	public String demo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username;
		if (principal instanceof UserDetails) {
			System.out.println("vao day 1");
			username = ((UserDetails) principal).getUsername();
		} else {
			//Nó sẽ vào đây, vì thằng principal thuộc kiểu String
			System.out.println("vao day 2");
			username = principal.toString();
		}

		return "<h3>This is a demo about principal</h3><br/>Username = " + username;
	}
	/*
	 * Trong đoạn code trên, chúng ta có được một principal từ đối 
	 * tượng Authentication. Principal đơn giản chỉ là một đối tượng 
	 * và sẽ được ép kiểu sang UserDetails.
	 * UserDetails là một interface cốt lõi của Spring Security. Nó đại diện 
	 * cho một principal nhưng theo một cách mở rộng và cụ thể hơn
	 */

	@RequestMapping("/demo2")
	@ResponseBody
	public String demo2(HttpServletRequest request) {
		return "Full name = " + request.getSession().getAttribute("userFullName");
	}
}
