package hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
// không cần bọn sau vì Spring boot đã config hết rồi!
// @EnableWebMvc
// @ComponentScan
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	/**
	 * To resolve JSP files location, you can have two approaches. 
	 * 1) Add entries in application.properties
	 * 2) Configure InternalResourceViewResolver, like this:
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		registry.viewResolver(viewResolver);
	}
}
