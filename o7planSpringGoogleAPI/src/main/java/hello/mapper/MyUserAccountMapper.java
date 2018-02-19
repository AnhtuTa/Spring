package hello.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import hello.model.MyUserAccount;
import org.springframework.jdbc.core.RowMapper;

/*
 * MyUserAccountMapper là lớp giúp ánh xạ (mapping) giữa các trường (field) của 
 * lớp MyUserAccount với các cột của bảng USER_ACCOUNTS.
 */
public class MyUserAccountMapper implements RowMapper<MyUserAccount> {

	@Override
	public MyUserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id = rs.getString("id");

		String email = rs.getString("email");
		String userName = rs.getString("user_name");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String password = rs.getString("password");
		String role = rs.getString("role");

		return new MyUserAccount(id, email, userName, firstName, 
				lastName, password, role);
	}
}
