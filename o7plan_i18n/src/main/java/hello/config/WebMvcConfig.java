package hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

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
		configurer.enable();
	}

	//
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		 LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		 localeInterceptor.setParamName("lang");
		
		 registry.addInterceptor(localeInterceptor).addPathPatterns("/*");
	}
}
