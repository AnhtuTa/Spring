package example2.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import example2.entities.Customer;
import example2.entities.CustomerMapper;

@Repository
@Transactional
public class CustomerDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public CustomerDAO() {
		System.out.println("This is constructor of CustomerDAO.java");
	}
	
	public void save(Customer c) {
		String sql = "INSERT INTO customer(name, address) VALUES (?, ?)";
		jdbcTemplate.update(sql, c.getName(), c.getAddress());
	}

	public void delete(int id) {
		String sql = "DELETE FROM customer WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void update(Customer c) {
		String sql = "UPDATE customer SET name = ?, address = ? WHERE id = ? ";
		jdbcTemplate.update(sql, c.getName(), c.getAddress(), c.getId());
	}

	public Customer findById(int id) {
		String sql = "SELECT * FROM customer WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new CustomerMapper(), id);
	}

	public List<Customer> findAll() {
		String sql = "SELECT * FROM customer";
		return jdbcTemplate.query(sql, new CustomerMapper());
	}

}
