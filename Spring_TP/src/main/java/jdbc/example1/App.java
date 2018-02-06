package jdbc.example1;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans6.xml");
		CustomerDAO cd = (CustomerDAO) context.getBean("customerDAO_using_autowire");
		//=============
		cd.createDatabase();
		
		//=============
		//cd.save(new Customer("Anhtuta", "Hanoi"));
		//cd.save(new Customer(11, "Ronaldo", "BDN"));
		
		//=============
		Customer c1 = cd.getCustomer(7);
		System.out.println(c1.getInfo());
		
		//=============
		List<Customer> cList = cd.getAllCustomers();
		for(Customer c : cList) {
			c.printInfo();
		}
		
		//=============
		cd.update(new Customer(7, "Anhtu_updated", "Hanoi"));
		c1 = cd.getCustomer(7);
		c1.printInfo();
		
		//=============
		cd.delete(12);
		cList = cd.getAllCustomers();
		for(Customer c : cList) {
			c.printInfo();
		}
		
		//=============
		Customer c2 = cd.getCustomerUsingPS(7);
		c2.printInfo();

		((ConfigurableApplicationContext)context).close();
		
	}
}
