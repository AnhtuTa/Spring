package hello.aspectj;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
 * Tạo advice cho tất cả các method trong package hello.controller
 */
@Component
@Aspect
public class AspectJ_Controllers {
	private Map<String, Integer> accessTimesMap;

	public AspectJ_Controllers() {
		accessTimesMap = new HashMap<>();
	}

	// pointcut cho tất cả các method trong package demo.controller
	@Pointcut("execution(* hello.controller..*.*(..))")
	public void publicMethodsInsideStudentRestController() {}

	@Before("publicMethodsInsideStudentRestController()")
	public void countAccessTimes(JoinPoint jp) {
		String classOfCalledMethod = jp.getTarget().getClass().getSimpleName();
		String calledMethod = jp.getSignature().getName();
		String key = classOfCalledMethod + "." + calledMethod;
		// System.out.println("\n============ Begin " + key + "() ============");
		
		Integer currTimes = accessTimesMap.get(key);
		
		if(currTimes == null) {
			currTimes = 1;
		} else {
			currTimes++;
		}
		
		accessTimesMap.put(key, currTimes);
		// System.out.println("Calling method '" + key + "()' " + currTimes + " times");
	}
	
	@After("publicMethodsInsideStudentRestController()")
	public void printEndOfMethod(JoinPoint jp) {
//		String classOfCalledMethod = jp.getTarget().getClass().getSimpleName();
//		String calledMethod = jp.getSignature().getName();
//		String key = classOfCalledMethod + "." + calledMethod;
//		System.out.println("============ End of " + key + "() ============\n");
	}
}
