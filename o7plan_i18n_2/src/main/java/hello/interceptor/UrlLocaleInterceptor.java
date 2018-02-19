package hello.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 
 * @author AnhTu
 * Các URL có dạng /o7plan_i18n_2/language_name/... sẽ đi qua interceptor này.
 * Nó có nhiệm vụ kiểm tra xem localeResolver = null hay ko
 * (language_name = {vi, ja, en}, việc thiết lập này xem file WebMvcConfig.java:
 * @see hello.config.WebMvcConfig)
 * Nếu ko == null thì là lỗi phần server, developer cần fix bug!
 */
public class UrlLocaleInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if (localeResolver == null) {
			throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
		}
		// Lấy ra thông tin Locale từ LocaleResolver
		Locale locale = localeResolver.resolveLocale(request);
		System.out.println("[UrlLocaleInterceptor] " + locale.getLanguage() + ", " + locale.getCountry());
		localeResolver.setLocale(request, response, locale);
		return true;
	}

}