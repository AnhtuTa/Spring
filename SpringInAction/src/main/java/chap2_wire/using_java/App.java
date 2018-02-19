package chap2_wire.using_java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(CDPlayerConfig.class);
		CDPlayer cdPlayer = (CDPlayer) context.getBean("HoQuangHieuCD");
		cdPlayer.play();
		
		((ConfigurableApplicationContext) context).close();
	}
}
