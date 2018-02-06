package java_based_config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import beans.Student;

public class ConfigDemo {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(StudentConfig.class);
		//Student st = context.getBean(Student.class);
		Student st = (Student) context.getBean("demo");
		System.out.println(st.getInfo());

		((ConfigurableApplicationContext)context).close();
		
	}
}
