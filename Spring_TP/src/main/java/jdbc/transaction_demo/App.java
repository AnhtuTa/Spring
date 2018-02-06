package jdbc.transaction_demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

// Xem thêm ở file BankAccountDAO.java, TranDemo.java, project SJ_Transaction
public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans6.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		
		PlatformTransactionManager transactionManager = 
				(PlatformTransactionManager) context.getBean("transactionManager");
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			String sql = "UPDATE bank_account SET amount = ? WHERE name = ?";
			jdbcTemplate.update(sql, 43210000, "anhtu3");
			jdbcTemplate.update(sql, 150100200, "huy3");
			
			transactionManager.commit(status);
		} catch(DataAccessException e) {
			System.out.println("Error! rolling back");
			transactionManager.rollback(status);
		}

		((ConfigurableApplicationContext)context).close();
	}

}
/*
Giá trị trong CSDL:
id	name	amount
--------------------
5	anhtu3	40000000
6	huy3	90000000
 */
