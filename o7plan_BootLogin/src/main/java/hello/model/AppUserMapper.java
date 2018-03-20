package hello.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/*
 * Lớp AppUserMapper được sử dụng để ánh xạ (mapping) 
 * các cột trong bảng APP_USER với các trường (field) 
 * trong lớp AppUser (Dựa trên câu lệnh truy vấn).
 */
public class AppUserMapper implements RowMapper<AppUser> {

	@Override
	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new AppUser(rs.getLong("id"), rs.getString("user_name"), 
				rs.getString("encryted_password"));
	}
}
