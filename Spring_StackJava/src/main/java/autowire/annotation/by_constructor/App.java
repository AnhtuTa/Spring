package autowire.annotation.by_constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("autowire_annotation_by_constructor.xml");
		Person person = (Person) context.getBean("person");
		person.print();

		((ConfigurableApplicationContext)context).close();
	}
}
