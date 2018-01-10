package prop_file_demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("prop_demo.xml");
		DataResource dr = (DataResource) context.getBean("dataResource");
		System.out.println(dr.getJDBCInfo());
		((ConfigurableApplicationContext)context).close();
	}
}
