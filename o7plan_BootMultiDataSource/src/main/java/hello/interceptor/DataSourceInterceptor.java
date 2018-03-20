package hello.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * DataSourceInterceptor sẽ phân tích URL của request, 
 * và quyết định Datasource nào sẽ được sử dụng.
 */
public class DataSourceInterceptor extends HandlerInterceptorAdapter {
	// Request:
	// /publisher/list
	// /advertiser/list
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {

		String contextPath = request.getServletContext().getContextPath();

		// /SomeContextPath/publisher
		String prefixPublisher = contextPath + "/publisher";

		// /SomeContextPath/advertiser
		String prefixAdvertiser = contextPath + "/advertiser";

		// /SomeContextPath/publisher/dashboard
		// /SomeContextPath/advertiser/dashboard
		String uri = request.getRequestURI();

		if (uri.startsWith(prefixPublisher)) {
			request.setAttribute("keyDS", "PUBLISHER_DS");
		}

		else if (uri.startsWith(prefixAdvertiser)) {
			request.setAttribute("keyDS", "ADVERTISER_DS");
		}

		// Just for test
		String url = request.getRequestURL().toString();
		String contextPath2 = request.getContextPath();
		System.out.println("URI:" + uri);
		System.out.println("URL:" + url);
		System.out.println("contextPath:" + contextPath);
		System.out.println("contextPath2:" + contextPath2);
		
		/*
		 VD với URL = http://localhost:8080/advertiser/list
		 URI = /advertiser/list
		 contextPath = 		(rỗng, ko có)
		 
		 Chú ý, với các project SpringMVC trước thì có contextPath, ví dụ:
		 URL = http://localhost:8080/o7plan_HelloSpring/advertiser/list
		 thì contextPath = o7plan_HelloSpring
		 URI = o7plan_HelloSpring/advertiser/list
		 */
		return true;
	}

}
