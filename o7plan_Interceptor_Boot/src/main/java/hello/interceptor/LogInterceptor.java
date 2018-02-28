package hello.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println("\n-------- LogInterception.preHandle --- ");
		System.out.println("Request URL: " + request.getRequestURL());
		System.out.println("Start Time: " + System.currentTimeMillis());

		request.setAttribute("startTime", startTime);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler,	ModelAndView modelAndView) throws Exception {
		System.out.println("\n-------- LogInterception.postHandle --- ");
		System.out.println("Request URL: " + request.getRequestURL());

		// Ở đây, bạn có thể add các attribute vào modelAndView
		// Và sử dụng nó trong các View (jsp,..)
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		System.out.println("\n-------- LogInterception.afterCompletion --- ");

		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		System.out.println("Request URL: " + request.getRequestURL());
		System.out.println("End Time: " + endTime);

		System.out.println("Time Taken: " + (endTime - startTime));
	}
}
