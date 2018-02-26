package hello.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hello.model.UserInfo;

/*
 * Lớp UserInfoMapper sử dụng để ánh xạ các cột trong câu 
 * lệnh SQL tương ứng với các trường (field) của lớp UserInfo.
 */
public class UserInfoMapper implements RowMapper<UserInfo> {

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		String userName = rs.getString("Username");
		String password = rs.getString("Password");

		return new UserInfo(userName, password);
	}

}
