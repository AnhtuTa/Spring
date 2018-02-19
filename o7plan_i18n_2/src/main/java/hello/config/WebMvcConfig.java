package hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import hello.interceptor.UrlLocaleInterceptor;

/*
 * Trước khi request được xử lý bởi Controller, nó phải đi qua các 
 * Interceptors, ở đây bạn cần đăng ký LocaleChangeInterceptor, 
 * Interceptor này xử lý các thay đổi Locale từ phía người dùng.
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	// Cấu hình để sử dụng các file nguồn tĩnh (html, image, ..)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		System.out.println("This is WebMvcConfig");
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		UrlLocaleInterceptor localeInterceptor = new UrlLocaleInterceptor();
		registry.addInterceptor(localeInterceptor).addPathPatterns("/en/*", "/ja/*", "/vi/*");
		/*
		 * Chỉ những URL có dạng:
		 * /o7plan_i18n_2/en/...,
		 * /o7plan_i18n_2/vi/...,
		 * /o7plan_i18n_2/ja/...
		 * thì mới đi qua interceptor này
		 */
	}
}
