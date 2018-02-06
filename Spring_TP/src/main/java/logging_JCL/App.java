package logging_JCL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import logging_log4j.HelloWorld;

public class App {
	static Log log = LogFactory.getLog(App.class.getName());
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans7.xml");
		
		log.info("[JCL] Going to crete HelloWorld object");
		HelloWorld hw = (HelloWorld) context.getBean("helloWorld");
		System.out.println("Message = " + hw.getMessage());
		log.info("[JCL] Exiting the program");
		
		log.fatal("[JCL] This is fatal message");
		log.error("[JCL] This is error message");
		log.warn("[JCL] This is warn message");
		log.debug("[JCL] This is debug message");
		log.trace("[JCL] This is trace message");
		
		((ConfigurableApplicationContext)context).close();
	}
}
