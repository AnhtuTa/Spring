package example2.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import example2.entities.Customer;
import example2.entities.CustomerMapper;

/*
 * Khi dùng các query INSERT, UPDATE, DELETE thì ta dùng hàm update()
 * Khi dùng query SELECT thì ta dùng hàm queryForXxx()
 */
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
	
	public List<Customer> getCustomersByName(String name) {
		String sql = "SELECT * FROM customer WHERE name = ?";
		return jdbcTemplate.query(sql, new CustomerMapper(), name);
	}
	
	public List<Map<String,Object>> getCustomersByName2(String name) {
		String sql = "SELECT * FROM customer WHERE name = ?";
		return jdbcTemplate.queryForList(sql, name);
		// Chú ý rằng: do queryForList trả về 1 list các map, mà mỗi map có kiểu key=value, trong đó
		// value là 1 đố tượng object (thường là String), do đó ko liên quan đến
		// thằng RowMapper. Do vậy ko cần xài thằng CustomerMapper
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("app_context.xml");
		
		CustomerDAO cd = new CustomerDAO();
		cd.jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		List<Customer> cList = cd.getCustomersByName("Anhtuta");
		for(Customer c : cList) {
			System.out.println(c.getInfo());
		}
		
		System.out.println("========================");
		
		List<Map<String,Object>> cMapList = cd.getCustomersByName2("Anhtuta");
		System.out.println(cMapList);
		System.out.println("Display in JSON format");
		for(Map<String,Object> map : cMapList) {
			System.out.print("{");
			for(String key : map.keySet()) {
				//System.out.print(key + "=" + map.get(key) + ", ");
				System.out.print("\"" + key + "\": " + "\"" + map.get(key) + "\", ");
			}
			System.out.println("}");
		}
		
		System.out.println("Display in XML format");
		for(Map<String,Object> map : cMapList) {
			System.out.println("<customer>");
			for(String key : map.keySet()) {
				System.out.println("\t<" + key + ">" + map.get(key) + "</" + key + ">");
			}
			System.out.println("</customer>");
		}
		
		((ConfigurableApplicationContext) context).close();
	}
}
