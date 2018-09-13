package demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * To resolve JSP files location, you can have two approaches. 1) Add entries in
	 * application.properties 2) Configure InternalResourceViewResolver, like this:
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		registry.viewResolver(viewResolver);
	}
	
	/**
	 * Xem thêm tại: http://www.baeldung.com/spring-mvc-static-resources
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Cần tạo các thư mục sau trong classpath: /META-INF/resources/static/...
		// Ngày trước hay làm cách này, nhưng nếu deploy project lên Tomcat thì ko đc nữa,
		// vì lúc đấy ứng dụng sẽ có thêm ContextPath, nghĩa là nó sẽ chạy trên URL:
		// localhost:8080/Context-Path/, khi đó cách config sau bị lỗi!
		// Nếu ko muốn tạo thư mục META-INF/resources/static trong classpath
		// thì cần tạo thư mục static/wtf trong thư mục webapp là đc!
		registry.addResourceHandler("/wtf/**").addResourceLocations("/static/wtf/");

		// Nếu muốn dùng thư mục static có sẵn thì làm như sau (Nên dùng cách này):
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
		
		// the actual file will be located in the WAR’s webapp/resources folder
		// Nghĩa là nó sẽ đi vào thư mục /webapp/resources/**
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
		// tương tự cái trên, nhưng có thể dùng 2 thư mục khác nhau
		registry.addResourceHandler("/resources2/**")
	    	.addResourceLocations("/resources2/","/other-resources2/")
	    	.setCachePeriod(60)
	        .resourceChain(true)
	        .addResolver(new PathResourceResolver());
//		We are registering the PathResourceResolver in the resource chain as the sole 
//		ResourceResolver in it. See section 4.3. to check how to chain more than one ResourceResolver.
//		The resources served will be cached in the browser for 60 seconds.
//		The chain is finally configured with the method resourceChain(true)
	}

}
