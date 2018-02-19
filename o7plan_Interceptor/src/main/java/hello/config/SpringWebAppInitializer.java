package hello.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/*
 * SpringWebAppInitializer là class thi hành (implements) interface
 * WebApplicationInitializer. Spring sẽ đọc các thông tin cấu hình 
 * trong class này để bắt đầu (initial) ứng dụng Web của bạn. 
 * 
 * Thông thường trong lớp này bạn có thể đăng ký các Servlet, 
 * các Servlet Filter, và Servlet Listener thay cho việc đăng ký chúng trong web.xml. 
 * 
 * Một servlet quan trọng là org.springframework.web.servlet.DispatcherServlet, 
 * bạn cần đăng ký nó.
 */
public class SpringWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(ApplicationContextConfig.class);

		// Dispatcher Servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
				new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		dispatcher.setInitParameter("contextClass", context.getClass().getName());

		servletContext.addListener(new ContextLoaderListener(context));

		// UTF8 Charactor Filter.
		FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);

		fr.setInitParameter("encoding", "UTF-8");
		fr.setInitParameter("forceEncoding", "true");
		fr.addMappingForUrlPatterns(null, true, "/*");
	}

}
