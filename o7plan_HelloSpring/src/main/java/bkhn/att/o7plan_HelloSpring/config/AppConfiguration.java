package bkhn.att.o7plan_HelloSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import bkhn.att.o7plan_HelloSpring.lang.Language;
import bkhn.att.o7plan_HelloSpring.lang_impl.Vietnamese;

/*
 * @Configuration là một annotation, nó được chú thích trên một class,
 * class này sẽ định nghĩa các Spring BEAN. 
 * @ComponentScan - Nói cho Spring các package để tìm kiếm
 * các Spring BEAN khác, Spring sẽ quét (scan) các package đó để tìm kiếm.
 * Các Spring BEAN được tạo ra sẽ được quản lý trong Spring IoC Container
 * (Bộ chứa Spring IoC).
 */
@Configuration
@ComponentScan({"bkhn.att.o7plan_HelloSpring.bean"})
public class AppConfiguration {

	@Bean(name="language")
	public Language getVie() {
		return new Vietnamese();
	}
}
