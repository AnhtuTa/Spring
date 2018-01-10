package annotation_config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Anno_Autowired {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans4.xml");
		Person p = (Person) context.getBean("p1");
		System.out.println(p.getInfo());
	}

}
