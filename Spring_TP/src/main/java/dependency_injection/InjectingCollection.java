package dependency_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.JavaCollection2;

public class InjectingCollection {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans2.xml");
		JavaCollection2 jc2 = (JavaCollection2) context.getBean("javaCol2");
		jc2.getCityList();
		jc2.getCityMap();
		jc2.getCitySet();
		jc2.getCityProp();
		
	}

}
