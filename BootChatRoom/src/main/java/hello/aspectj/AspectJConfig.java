package hello.aspectj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
 * Chỉ cần config = các annotation sau là đủ!
 */
@Configuration
@ComponentScan	//just scan this package
@EnableAspectJAutoProxy
public class AspectJConfig {
	
}
