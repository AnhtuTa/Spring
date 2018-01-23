package spring_aop.config_using_xml;

import org.springframework.aop.ThrowsAdvice;

public class DemoThrowAdvice implements ThrowsAdvice {
	public void afterThrowing(IllegalArgumentException e) throws Throwable {
		System.out.println("throw advice method: ");
	}

}
