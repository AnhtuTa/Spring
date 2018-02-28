package hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*
 * Chú ý: JSP ViewResolver phải được thiết lập với độ ưu tiên 
 * thấp nhất (Cao nhất chứ nhỉ???). 
 * Điều này được ghi chú trong các tài liệu của Spring.
 */
@Configuration
public class JspViewResolverConfig {
	@Bean(name = "viewResolver")
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsps/");
		viewResolver.setSuffix(".jsp");
		// viewResolver.setContentType("text/html");

		// Make sure > Thymeleaf order & FreeMarker order.
		viewResolver.setOrder(1000);

		return viewResolver;
	}
}
