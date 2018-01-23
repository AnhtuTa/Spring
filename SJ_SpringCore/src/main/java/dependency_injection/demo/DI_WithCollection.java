package dependency_injection.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dependency_injection.beans.Student;

public class DI_WithCollection {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");
		Student st1 = (Student) context.getBean("st1");
		st1.printInfo();
		Student st2 = (Student) context.getBean("st2");
		st2.printInfo();
		
		((ConfigurableApplicationContext)context).close();
	}

}
