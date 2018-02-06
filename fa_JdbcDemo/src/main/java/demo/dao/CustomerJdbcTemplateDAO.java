package demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import demo.model.Customer;

@Component
public class CustomerJdbcTemplateDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertCustomer(Customer c) {
		String sql = "insert into customer(name, address) values (?, ?)";
		jdbcTemplate.update(sql, c.getName(), c.getAddress());
	}
}
