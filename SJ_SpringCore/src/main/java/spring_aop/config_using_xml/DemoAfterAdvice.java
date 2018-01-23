package spring_aop.config_using_xml;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class DemoAfterAdvice implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("after method: " + method.getName());
	}

}
