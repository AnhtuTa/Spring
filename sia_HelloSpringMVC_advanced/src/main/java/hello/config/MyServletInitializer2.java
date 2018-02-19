package hello.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.FilterRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;

import hello.app.MyFilter;

public class MyServletInitializer2 implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		Dynamic filter = 
			servletContext.addFilter("myFilter", MyFilter.class);
		filter.addMappingForUrlPatterns(null, false, "/custom/**");
	}
}
