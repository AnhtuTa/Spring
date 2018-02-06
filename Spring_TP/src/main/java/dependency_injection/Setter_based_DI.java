package dependency_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Setter_based_DI {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		CodeEditor ce = (CodeEditor) context.getBean("codeEditor");
		ce.codeCheck();

		((ConfigurableApplicationContext)context).close();
	}
}
