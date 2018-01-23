package spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TranDemo {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		BankAccountDAO bad = (BankAccountDAO) context.getBean("bankAccountDAO");
		
		List<BankAccount> baList = new ArrayList<BankAccount>();
		baList.add(new BankAccount("anhtu2", 4321000));
		baList.add(new BankAccount("huy2", 140000000));
		bad.updateBankAccount(baList);
		
		((ClassPathXmlApplicationContext) context).close();
		
	}
}
/*
Giá trị trong CSDL:
id	name	amount
--------------------
3	anhtu2	40000000
4	huy2	90000000
 */