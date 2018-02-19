package chap2_wire.automatic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(CDPlayerConfig.class);
		SgtPeppers sgtPeppers = (SgtPeppers) context.getBean("sgtPeppers");
		sgtPeppers.play();
		
		((ConfigurableApplicationContext) context).close();
	}
}
