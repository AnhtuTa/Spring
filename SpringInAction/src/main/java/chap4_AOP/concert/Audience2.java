package chap4_AOP.concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience2 {

	@Pointcut("execution(* chap4_AOP.concert.Performance.perform(..))")
	public void performance() {}
	
	@Around("performance()")
	public void watchPerformance(ProceedingJoinPoint pj) {
		try {
			System.out.println("\n[Advice] Starting an performance...\n[Advice] Audiences now are silenceing their cell phones");
			System.out.println("[Advice] Everyone is taking seats");
			pj.proceed();
			System.out.println("[Advice] The perform was so great!!! Everyone is applausing! CLAP CLAP CLAP!!!!");
		} catch(Throwable e) {
			System.err.println("[Advice] Suck! The performance is terrible! Everybody needs the refund!");
		}
		System.out.println("[Advice] The performance is over, everyone is leaving...");
	}
	/*
	 * Khi dùng Audience2 thì thứ tự các việc println đúng theo thứ tự ta code ở trên
	 * Nhưng khi dùng Audience thì thứ tự hơi khác, cụ thể:
	 * thằng @After("performance()") sẽ được thực hiện trước thằng @AfterReturning("performance()")
	 */
}
