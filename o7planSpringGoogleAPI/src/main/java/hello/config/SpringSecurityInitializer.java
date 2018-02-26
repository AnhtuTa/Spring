package hello.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//Config: Spring Security Filter.
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	public SpringSecurityInitializer() {
		System.out.println("This is SpringSecurityInitializer");
	}
}