package autowire.annotation.by_type;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("autowire_annotation_by_type.xml");
		Person person = (Person) context.getBean("person");
		person.print();

		((ConfigurableApplicationContext)context).close();
	}
}
