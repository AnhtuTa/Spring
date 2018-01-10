package java_based_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import beans.Student;

@Configuration
public class StudentConfig {
	@Bean(name="demo")
	public Student std1() {
		return new Student("Anhtu95", "Ha Noi", "HUST");
	}
	/*
	 * The above code will be equivalent to the following XML configuration:
		<beans>
			<bean id="std1" class="beans.Student" />
		</beans>
	 */
}
