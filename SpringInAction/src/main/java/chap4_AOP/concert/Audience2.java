package chap4_AOP.concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience2 {

	@Pointcut("execution(* chap4_AOP.concert.Performance.perform(..))")
	public void performance() {
	}

	/**
	 * Around advice là kiểu advice powerful nhất. Nó giống như viết cả before
	 * advice và after advice trong 1 single advice method
	 * 
	 * @param pj có kiểu ProceedingJoinPoint, nó dùng để invoke method 
	 * performance() trong advice của bạn!
	 * The advice method will do everything it needs to do, and when it's ready to
	 * pass control to the advised method, it will call proceed() method
	 * Nếu không gọi hàm proceed() thì advice sẽ chặn access của advised method
	 */
	@Around("performance()")
	public void watchPerformance(ProceedingJoinPoint pj) {
		try {
			// Before advice
			System.out.println("\n[Advice] Starting an performance...\n[Advice] "//
					+ "Audiences now are silenceing their cell phones");
			System.out.println("[Advice] Everyone is taking seats");
			
			// performing (thực hiện hàm performance())
			pj.proceed();

			// After Returning advice
			System.out.println("[Advice] The perform was so great!!! "//
					+ "Everyone is applausing! CLAP CLAP CLAP!!!!");
		} catch (Throwable e) {
			// After Throwing advice
			System.err.println("[Advice] Suck! The performance is terrible! "//
					+ "Everybody needs the refund!");
		}
		
		// after advice
		System.out.println("[Advice] The performance is over, everyone is leaving...");
	}
	/*
	 * Khi dùng Audience2 thì thứ tự các việc println đúng theo thứ tự ta code ở
	 * trên Nhưng khi dùng Audience thì thứ tự hơi khác, cụ thể:
	 * thằng @After("performance()") sẽ được thực hiện trước
	 * thằng @AfterReturning("performance()")
	 */
}
