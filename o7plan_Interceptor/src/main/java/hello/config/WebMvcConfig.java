package hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import hello.interceptor.AdminInterceptor;
import hello.interceptor.LogInterceptor;
import hello.interceptor.OldLoginInterceptor;

/*
 * Class WebMvcConfig mở rộng từ WebMvcConfigurerAdapter,
 * sử dụng để cấu hình các tài nguyên sử dụng trong 
 * Spring MVC, chẳng hạn các nguồn tài nguyên tĩnh (Image, CSS, Javascript,..)
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
	
		// LogInterceptor áp dụng cho mọi URL.
		registry.addInterceptor(new LogInterceptor());

		// Đường dẫn login cũ, không còn sử dụng nữa.
		// Sử dụng OldURLInterceptor để điều hướng tới một URL mới.
		registry.addInterceptor(new OldLoginInterceptor())//
				.addPathPatterns("/admin/oldLogin");

		// Interceptor này áp dụng cho các URL có dạng /admin/*
		// Loại đi trường hợp /admin/oldLogin
		registry.addInterceptor(new AdminInterceptor())//
				.addPathPatterns("/admin/*")//
				.excludePathPatterns("/admin/oldLogin");
	}
}
