package chap4_AOP.concert;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/*
 * A performance isn’t a performance without an audience. Or is it? 
 * When you think about it from the perspective of a performance, 
 * an audience is important but isn’t central to the function of 
 * the performance itself; it’s a separate concern. Therefore, it
 * makes sense to define the audience as an aspect that’s applied 
 * to a performance
 * 
 * Audience class is annotated with @Aspect. This annotation indicates
 * that Audience isn’t just any POJO—it’s an aspect
 * Audience has four methods that define things an audience might 
 * do as it observes a performance. Before the performance, the audience 
 * should take their seats (takeSeats()) and silence their cell phones 
 * (silenceCellPhones()). If the performance goes well,  the audience 
 * should applaud (applause()). But if the performance fails to meet 
 * the audience’s expectations, then the audience should demand a 
 * refund (demandRefund()).
 */
@Aspect
public class Audience {
	//define named pointcut
	@Pointcut("execution(* chap4_AOP.concert.Performance.perform(..))")
	public void performance() {}
	
	//Before performance
	@Before("performance()")
	public void silenceCellPhones() {
		System.out.println("\n[Advice] Starting an performance...\n[Advice] Audiences now are silenceing their cell phones");
	}
	
	//Before performance
	@Before("performance()")
	public void takeSeats() {
		System.out.println("[Advice] Everyone is taking seats");
	}
	
	//After performance
	@AfterReturning("performance()")
	public void applause() {
		System.out.println("[Advice] The perform was so great!!! Everyone is applausing! CLAP CLAP CLAP!!!!");
	}
	
	//After bad performance
	@AfterThrowing("performance()")
	public void demandRefund() {
		System.err.println("[Advice] Suck! The performance is terrible! Everybody needs the refund!");
	}
	
	//After performance, even if it throwed a exception or not!
	@After("performance()")
	public void finishPerformance() {
		System.out.println("[Advice] The performance is over, everyone is leaving...");
	}
}
