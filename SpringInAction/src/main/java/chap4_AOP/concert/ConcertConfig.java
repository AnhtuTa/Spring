package chap4_AOP.concert;

//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan	//just scan this package
@EnableAspectJAutoProxy
public class ConcertConfig {
	//Dùng 1 trong 2 cái sau (Audience hoặc Audience2) để test!
//	@Bean
//	public Audience audience_this_is_an_aspect() {
//		return new Audience();
//	}
	
	@Bean
	public Audience2 audience2_this_is_an_aspect() {
		return new Audience2();
	}
	
	@Bean(name = "emCuaNgayHomQua")
	//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public EmCuaNgayHomQua_SonTungMTP ecnhq() {
		return new EmCuaNgayHomQua_SonTungMTP();
	}
	
	@Bean(name = "emGaiMua")
	public EmGaiMua_HuongTram egm() {
		return new EmGaiMua_HuongTram();
	}
}
