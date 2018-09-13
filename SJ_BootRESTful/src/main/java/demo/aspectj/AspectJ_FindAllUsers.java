package demo.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;

/*
 * Tạo advice cho method getAllStudents() trong class StudentRestController
 */
//Demo 1 trong 2 cái: class này hoặc là AspectJ_StudentREST
//@Component
@Aspect
public class AspectJ_FindAllUsers {
	private int totalAccessTimes = 0;
	
	// define named pointcut
	@Pointcut("execution(* demo.controller.StudentRestController.getAllStudents(..))")
	public void getAllUsersPointcut() {
	}

	// Before calling getAllUsers
	@Before("getAllUsersPointcut()")
	public void countAccessTimes() {
		totalAccessTimes++;
		System.out.println("totalAccessTimes = " + totalAccessTimes);
	}
}
