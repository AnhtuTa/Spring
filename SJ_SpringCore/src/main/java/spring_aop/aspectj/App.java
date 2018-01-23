package spring_aop.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("aop_aspectj.xml");
		Hello hello = (Hello) context.getBean("hello");
		hello.method1();
		hello.method2();
		hello.method3();
		
		((ConfigurableApplicationContext)context).close();
	}

}
