package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

// initialize the Servlet context required by Tomcat by implementing the SpringBootServletInitializer interface
@SpringBootApplication
public class SjBootBannerApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(SjBootBannerApplication.class, args);
	}
}
