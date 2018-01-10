package tutorialspoint;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Food;

public class BeanLifeCycle {

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Food food = (Food) context.getBean("banana");
		System.out.println(food.getInfo());
		context.registerShutdownHook();
	}

}
