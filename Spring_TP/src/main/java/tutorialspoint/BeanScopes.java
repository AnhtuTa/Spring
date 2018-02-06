package tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Student;

public class BeanScopes {

	public static void main(String[] args) {
		//TheSingletonScope
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Student st1 = (Student) context.getBean("st_singleton");
		st1.setName("Nguyen Bka");
		System.out.println(st1.getName());
		Student st2 = (Student) context.getBean("st_singleton");
		System.out.println(st2.getName());	//Nguyen Bka, vì đã setName ở trên rồi
		
		//ThePrototypeScope
		Student st3 = (Student) context.getBean("st_prototype");
		st3.setName("Huy ga");
		System.out.println(st3.getName());
		Student st4 = (Student) context.getBean("st_prototype");
		System.out.println(st4.getName());	//null, vì thằng này có scope là prototype


		((ConfigurableApplicationContext)context).close();
	}

}
