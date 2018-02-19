package chap4_AOP.concert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = 
			new AnnotationConfigApplicationContext(ConcertConfig.class);
//		Performance p1 = (Performance) context.getBean("emCuaNgayHomQua");
//		try {
//			p1.perform();
//		} catch (Exception e) {}
		
		Performance p2 = (Performance) context.getBean("emGaiMua");
		try {
			p2.perform();
		} catch (Exception e) {
			System.out.println("There's something wrong about Huong Tram...???");
			System.out.println(e.getMessage());
		}
		
		((ConfigurableApplicationContext) context).close();
	}
}
