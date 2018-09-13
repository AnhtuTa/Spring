package demo.aspectj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan	//just scan this package
@EnableAspectJAutoProxy
public class AspectJConfig {
	// Do class AspectJ_FindAllUsers đã có annotation @Component 
	// nên Bean sau sẽ tự động được tạo
//	@Bean
//	public AspectJ_FindAllUsers aspectJ_FindAllUsers() {
//		return new AspectJ_FindAllUsers();
//	}
}
