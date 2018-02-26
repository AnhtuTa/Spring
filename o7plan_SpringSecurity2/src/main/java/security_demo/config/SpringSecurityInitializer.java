package security_demo.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
 * If you like configuring servlets and filters in the traditional web.xml file, you can do
	that with the <filter> element, like this:
	<filter>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>
			org.springframework.web.filter.DelegatingFilterProxy
		</filter-class>
	</filter>
	The most important thing here is that the <filter-name> be set to springSecurityFilterChain.
	That’s because you’ll soon be configuring Spring Security for web security, 
	and there will be a filter bean named springSecurityFilterChain that
	DelegatingFilterProxy will need to delegate to.
	If you'd rather configure DelegatingFilterProxy in Java with a WebApplicationInitializer, 
	then all you need to do is create a new class that extends AbstractSecurityWebApplicationInitializer:

 * AbstractSecurityWebApplicationInitializer implements WebApplicationInitializer,
 * so it will be discovered by Spring and be used to register 
 * DelegatingFilterProxy with the web container.
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	 // Do nothing
}
