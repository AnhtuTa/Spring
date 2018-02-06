package bkhn.att.o7plan_HelloSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import bkhn.att.o7plan_HelloSpring.bean.GreetingService;
import bkhn.att.o7plan_HelloSpring.bean.MyComponent;
import bkhn.att.o7plan_HelloSpring.config.AppConfiguration;
import bkhn.att.o7plan_HelloSpring.lang.Language;

/*
 * Bước 1: Vào đây để đọc kỹ lý thuyết, có phần mô tả rất hay và dễ hiểu:
 * https://o7planning.org/vi/10127/huong-dan-lap-trinh-spring-cho-nguoi-moi-bat-dau#a720908
 * Bước 2: Nếu vẫn chưa hiểu, xem lại bước 1!!!
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = 
			new AnnotationConfigApplicationContext(AppConfiguration.class);
		
		Language language = (Language) context.getBean("language");
		System.out.println("Bean language = " + language);
		System.out.println(language.getGreeting());
		
		System.out.println("----------");
		
		GreetingService service = (GreetingService) context.getBean("greetingService");
		service.sayGreeting();
		
		System.out.println("----------");
		
		MyComponent component = (MyComponent) context.getBean("myComponent");
		component.showAppInfo();

		((ConfigurableApplicationContext)context).close();
	}
}
