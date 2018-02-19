package chap2_wire.using_xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		CDPlayer cdPlayer = (CDPlayer) context.getBean("HoQuangHieuCD");
		cdPlayer.play();
		
		((ConfigurableApplicationContext) context).close();
	}
}
