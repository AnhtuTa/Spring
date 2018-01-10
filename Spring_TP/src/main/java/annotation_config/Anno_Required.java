package annotation_config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Anno_Required {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans4.xml");
		Teacher tc = (Teacher) context.getBean("tc1");
		System.out.println(tc.getInfo());
	}

}
