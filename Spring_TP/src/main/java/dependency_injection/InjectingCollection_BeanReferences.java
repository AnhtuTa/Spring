package dependency_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.JavaCollection;

public class InjectingCollection_BeanReferences {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans2.xml");
		JavaCollection jc = (JavaCollection) context.getBean("javaCol");
		jc.getBookList();
		jc.getBookMap();
		jc.getBookSet();
		jc.getBookProp();

		((ConfigurableApplicationContext)context).close();
		
	}

}
