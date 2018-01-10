package event_handling;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Student;

public class EventDemo {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans5.xml");
		context.start();
		Student st = (Student) context.getBean("st1");
		System.out.println(st.getInfo());
		context.stop();
	}

}
