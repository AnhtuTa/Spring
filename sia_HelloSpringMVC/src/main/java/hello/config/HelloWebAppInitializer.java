package hello.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 *  Historically, servlets like DispatcherServlet have been configured in a web.xml
	file that’s carried in the web application’s WAR file. Certainly that’s one 
	option for configuring DispatcherServlet. But thanks to recent advances in
	the Servlet 3 specification and in Spring 3.1, it’s not the only option.
	And it’s not the option we’ll go with in this chapter.
	
	Instead of a web.xml file, you’re going to use Java to configure DispatcherServlet
	in the servlet container. The following listing shows the Java class you’ll need.
	
	To understand how listing 5.1 works, it’s probably sufficient to know that any class
	that extends AbstractAnnotationConfigDispatcherServletInitializer will automatically 
	be used to configure DispatcherServlet and the Spring application context in the 
	application’s servlet context
 */
public class HelloWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// I still didn't understand this thing is for what???
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Specify configuration class
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// Map DispatcherServlet to /
		return new String[] { "/" };
	}

}
