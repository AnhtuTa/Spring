package autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoWireByType {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans3.xml");
		TextEditor te = (TextEditor) context.getBean("te_bytype");
		te.spellCheck();
	}

}
