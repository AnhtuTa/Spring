package logging_log4j;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	static Logger logger = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans7.xml");
		
		logger.info("[Log4j] Going to crete HelloWorld object");
		HelloWorld hw = (HelloWorld) context.getBean("helloWorld");
		System.out.println("Message = " + hw.getMessage());
		logger.info("[Log4j] Exiting the program");
		
		((ConfigurableApplicationContext)context).close();
		
		//System.out.println(System.getProperty("user.dir"));
	}
}
