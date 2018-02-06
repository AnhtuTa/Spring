package jdbc.example1;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class CustomerDAO {
	private JdbcTemplate jdbcTemplate;
	
	public CustomerDAO() {
		System.out.println("constructor of CustomerDAO");
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void createDatabase() {
		String ddl = "CREATE TABLE IF NOT EXISTS Customer (" +
				"ID INT NOT NULL AUTO_INCREMENT," + 
				"NAME VARCHAR(45) NOT NULL," + 
				"AGE INT NOT NULL," + 
				"PRIMARY KEY (ID)" + 
				");";
		jdbcTemplate.execute(ddl);
	}
	
	public void save(Customer c) {
		if(c.getId() == -1) {
			String sql = "insert into customer(name, address) value (?, ?)";
			jdbcTemplate.update(sql, c.getName(), c.getAddress());
			System.out.println("inserted a customer with generated ID");
		} else {
			String sql = "insert into customer(id, name, address) value (?, ?, ?)";
			jdbcTemplate.update(sql, c.getId(), c.getName(), c.getAddress());
			System.out.println("inserted a customer with assigned ID");
		}
	}
	
	public Customer getCustomer(Integer id) {
		String sql = "select * from customer where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomerMapper());
	}
	
	public List<Customer> getAllCustomers() {
		String sql = "select * from customer";
		return jdbcTemplate.query(sql, new CustomerMapper());
	}
	
	public void update(Customer c) {
		String sql = "update customer set name = ?, address = ? where id = ?";
		jdbcTemplate.update(sql, c.getName(), c.getAddress(), c.getId());
	}
	
	public void delete(int id) {
		String sql = "delete from customer where id = ?";
		jdbcTemplate.update(sql, id);
	}
	
	public Customer getCustomerUsingPS(int id) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans6.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getCustomerById");
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
		Map<String, Object> out = jdbcCall.execute(in);
		
		Customer c = new Customer(id, (String) out.get("out_name"), (String) out.get("out_addr"));
		((ConfigurableApplicationContext)context).close();
		return c;
	}
}
