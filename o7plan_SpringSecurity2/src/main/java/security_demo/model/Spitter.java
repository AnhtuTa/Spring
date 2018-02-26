package security_demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/*
 * Class này có vai trò như UserInfo trong ví dụ trước
 */
public class Spitter {
	String username;
	String password;
	
	public Spitter() {}
	
	public Spitter(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static class SpitterMapper implements RowMapper<Spitter> {
		@Override
		public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Spitter(rs.getString("username"), rs.getString("password"));
		}
	}
}
