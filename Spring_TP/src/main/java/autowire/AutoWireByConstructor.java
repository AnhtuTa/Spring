package autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoWireByConstructor {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans3.xml");
		CodeEditor ce = (CodeEditor) context.getBean("ce_byconstructor");
		ce.spellCheck();
	}

}
