package hello.interceptors;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * 1 Interceptor sẽ gồm 3 method:
preHandle: xử lý trước khi request tới controller, nếu trả về false thì request sẽ không được gửi tới controller
postHandle: xử lý sau khi request tới controller
afterCompletion: thực hiện sau khi request hoàn thành
 */
public class LogTimeInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle Log time Interceptor: " + request.getServletPath() + new Date());
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle Log time Interceptor: " + request.getServletPath() + new Date());
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion Log time Interceptor: " + request.getServletPath() + new Date());
	}

}
