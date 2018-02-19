package hello.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import hello.resolver.UrlLocaleResolver;

/*
 * Bạn cần khai báo 2 Spring BEAN là localeResolver và messageResource. 
localeResolver - Chỉ định cách lấy thông tin địa phương (Locale) mà 
người dùng sẽ sử dụng. CookieLocaleResolver sẽ đọc thông tin Locale 
từ Cookie, để biết người dùng trước đó đã sử dụng trang web với ngôn ngữ nào. 

messageResource - Sẽ tải nội dung các file properties.
 */
@Configuration
@ComponentScan("hello")
public class ApplicationContextConfig {
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		System.out.println("This is ApplicationContextConfig");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean(name = "messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

		// Đọc vào file i18n/messages_xxx.properties
		// Ví dụ: i18n/message_en.properties
		messageResource.setBasename("classpath:i18n/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}

	// To solver URL like:
	// /o7plan_i18n_2/en/login
	// /o7plan_i18n_2/vi/login
	// /o7plan_i18n_2/jp/login
	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver() {
		return new UrlLocaleResolver();
	}
}
