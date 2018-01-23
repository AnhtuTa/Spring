package example2.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

//CustomerMapper dùng để convert dữ liệu query sang đối tượng Customer
public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Customer(rs.getInt("id"), 
				rs.getString("name"), rs.getString("address"));
	}
}
