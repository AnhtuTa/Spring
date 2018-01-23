package dependency_injection.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dependency_injection.beans.Person;

public class DI_WithObject {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Person p1 = (Person) context.getBean("person1");
		p1.print();
		Person p2 = (Person) context.getBean("person2");
		p2.print();
		((ConfigurableApplicationContext)context).close();
	}

}
