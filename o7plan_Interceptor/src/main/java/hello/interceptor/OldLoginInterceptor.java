package hello.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// OldLoginInterceptor là một chốt chặn (interceptor), nếu người dùng
// nhập vào đường dẫn /admin/oldLogin nó sẽ chuyển hướng tới đường dẫn mới là /admin/login.
public class OldLoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler)	throws Exception {

		System.out.println("\n-------- OldLoginInterceptor.preHandle --- ");
		System.out.println("Request URL: " + request.getRequestURL());
		System.out.println("Sorry! This URL is no longer used, Redirect to /admin/login");

		// Chú ý là ta dùng redirect chứ ko phải forward
		// request.getServletContext().getRequestDispatcher("another place").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/admin/login");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler,	ModelAndView modelAndView) throws Exception {

		// Đoạn code này sẽ không được chạy.
		System.out.println("\n-------- OldLoginInterceptor.postHandle --- ");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {

		// Đoạn code này sẽ không được chạy.
		System.out.println("\n-------- QueryStringInterceptor.afterCompletion --- ");
	}

}
