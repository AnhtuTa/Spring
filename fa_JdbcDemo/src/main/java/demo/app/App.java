package demo.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.dao.CustomerJdbcTemplateDAO;
import demo.model.Customer;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = 
			new ClassPathXmlApplicationContext("context.xml");

		// Do class CustomerJdbcTemplateDAO có annotation
		// là @Component, nên Spring BEAN tự động tạo ra 1 bean
		// dựa theo tên class này, cụ thể là bean customerJdbcTemplateDAO
		// đã đc tạo ra!
		CustomerJdbcTemplateDAO cjtd = 
				(CustomerJdbcTemplateDAO) context.getBean("customerJdbcTemplateDAO");
		cjtd.insertCustomer(new Customer("fresher", "fpt"));
		
		((ConfigurableApplicationContext) context).close();
	}
}
