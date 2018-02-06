package tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.HelloVietnam;
import beans.HelloWorld;

public class BeanDefinitionInheritance {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		HelloWorld hw = (HelloWorld) context.getBean("helloWorld");
		HelloVietnam hv = (HelloVietnam) context.getBean("helloVn");
		
		hw.getMessage();
		hw.getMessage2();
		
		hv.getMessage();	//message của hv kế thừa giá trị của message của hw
		hv.getMessage2();	//message2 của hv kế thừa giá trị của message2 của hw
		hv.getMessage3();


		((ConfigurableApplicationContext)context).close();
	}

}
