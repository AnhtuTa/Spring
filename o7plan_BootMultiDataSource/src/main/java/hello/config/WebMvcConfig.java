package hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import hello.interceptor.DataSourceInterceptor;

/*
 * Class này chỉ để thêm thằng Interceptor
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DataSourceInterceptor())//
				.addPathPatterns("/publisher/*", "/advertiser/*");
	}
}
