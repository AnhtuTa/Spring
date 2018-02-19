package hello.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;

import hello.app.MyServlet;

/*
 * this class is a rather basic servlet-registering initializer class. It registers a servlet and
maps it to a single path. You could use this approach to register DispatcherServlet
manually. (But there’s no need, because AbstractAnnotationConfigDispatcherServletInitializer does a fine job without as much code.)
 */
public class MyServletInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Register the servlet
		Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
		// Map the servlet
		myServlet.addMapping("/custom/**");
	}

}
