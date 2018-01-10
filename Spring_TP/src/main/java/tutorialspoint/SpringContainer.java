package tutorialspoint;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import beans.HelloWorld;
import beans.Student;

public class SpringContainer {

	public static void main(String[] args) {
		// First example of Spring (using ApplicationContext Container)
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		HelloWorld hw = (HelloWorld) context.getBean("helloWorld");
		hw.getMessage();
		
		Student st = (Student) context.getBean("mySt");
		System.out.println(st.getName());
		System.out.println(st.getAddr());
		System.out.println(st.getSchool());
		
		// Spring BeanFactory Container
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("Beans.xml"));
		Student st2 = (Student) factory.getBean("mySt");
		System.out.println(st2.getInfo());
		
		//Spring ApplicationContext Container, this time using FileSystemXmlApplicationContext
		ApplicationContext context2 = new FileSystemXmlApplicationContext(System.getProperty("user.dir") + "/src/main/java/Beans.xml");
		st = (Student) context2.getBean("mySt");
		System.out.println(st.getInfo());
		
	}

}
