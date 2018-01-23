package spring_aop.config_using_xml;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

//tạo proxy thực hiện cài đặt Before advice
public class DemoBeforeAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("before method: " + method.getName());
	}

}
