package spring_aop.config_using_xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("aop_using_xml.xml");
		Hello hello = (Hello) context.getBean("helloProxy");
		hello.method1();
		hello.method2();

		((ConfigurableApplicationContext)context).close();
	}

}
