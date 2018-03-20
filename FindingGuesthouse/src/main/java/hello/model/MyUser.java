package hello.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MyUser {
	Long id;
	String username;
	String fullname;
	String encryptedPassword;
	
	public MyUser() {}
	
	public MyUser(Long id, String username, String fullname, String encryptedPassword) {
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.encryptedPassword = encryptedPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	
	public static class MyUserMapper implements RowMapper<MyUser> {

		@Override
		public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new MyUser(rs.getLong("id"), rs.getString("username"), 
					rs.getString("fullname"), rs.getString("encrypted_password"));	
		}

	}
}
