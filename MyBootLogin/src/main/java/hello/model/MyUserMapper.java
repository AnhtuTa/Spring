package hello.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/*
 * Lớp MyUserMapper được sử dụng để ánh xạ (mapping) 
 * các cột trong bảng APP_USER với các trường (field) 
 * trong lớp MyUser (Dựa trên câu lệnh truy vấn).
 */
public class MyUserMapper implements RowMapper<MyUser> {

	@Override
	public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new MyUser(rs.getLong("USER_ID"), rs.getString("USER_NAME"),
			rs.getString("FULL_NAME"), rs.getString("ENCRYPTED_PASSWORD"));
	}
}
