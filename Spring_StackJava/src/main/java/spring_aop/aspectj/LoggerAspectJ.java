package spring_aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggerAspectJ {
	/*
	 * “execution(* spring_aop.aspectj.Hello.*(..))”: định nghĩa pointcut: ở đây
	 * thực hiện pointcut với tất cả các method của class Hello.java Ví dụ: đổi
	 * thành “execution(* spring_aop.aspectj.Hello.method2(..))” thì nó chỉ thực
	 * hiện pointcut với method2 của class Hello.java
	 */
	@Before("execution(* spring_aop.aspectj.Hello.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("\nbefore method: " + joinPoint.getSignature().getName());
	}

	@After("execution(* spring_aop.aspectj.Hello.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("after method: " + joinPoint.getSignature().getName());
	}

	// chỉ thực hiện log với method2 của Hello.java
	@AfterReturning(pointcut = "execution(* spring_aop.aspectj.Hello.method2(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("after return method : " + joinPoint.getSignature().getName());
		System.out.println("Method returned value is : " + result);
	}

	@AfterThrowing(pointcut = "execution(* spring_aop.aspectj.Hello.method3(..))", throwing = "error")
	public void logThrow(JoinPoint joinPoint, Throwable error) {
		System.out.println("exception in method: " + joinPoint.getSignature().getName());
		System.out.println("Exception is: " + error);
	}
}
