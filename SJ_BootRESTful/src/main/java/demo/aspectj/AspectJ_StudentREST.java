package demo.aspectj;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
 * Tạo advice cho tất cả các method trong package demo.controller
 */
@Component
@Aspect
public class AspectJ_StudentREST {
	private Map<String, Integer> accessTimesMap;

	public AspectJ_StudentREST() {
		accessTimesMap = new HashMap<>();
	}

	// pointcut cho tất cả các method trong package demo.controller
	@Pointcut("execution(* demo.controller..*.*(..))")
	public void publicMethodsInsideStudentRestController() {}

	@Before("publicMethodsInsideStudentRestController()")
	public void countAccessTimes(JoinPoint jp) {
//		System.out.println(jp.getTarget().getClass().getSimpleName());		//tên của class trong package
//		Method[] methods = jp.getTarget().getClass().getDeclaredMethods();
//		for(Method m: methods) {
//			System.out.println("\t" + m.getName());
//		}
//		
//		System.out.println(jp.getSignature().getDeclaringTypeName());
		
		String calledMethod = jp.getSignature().getName();
		Integer currTimes = accessTimesMap.get(calledMethod);
		
		if(currTimes == null) {
			currTimes = 1;
		} else {
			currTimes++;
		}
		
		accessTimesMap.put(calledMethod, currTimes);
		System.out.println("Calling method '" + jp.getTarget().getClass().getSimpleName() + //
				"." + calledMethod + "()' " + currTimes + " times");
	}
}
