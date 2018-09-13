package hello.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.jdbc.core.RowMapper;

public class MyUser {
	Long id;
	
	@NotBlank
	@Length(min=6, max=20)
	String username;
	
	@NotBlank
	@Email
	String email;
	
	@NotBlank
	String fullname;
	
	@NotBlank
	@Length(min=4, max=20)
	String password;	// trong database thì là field: encrypted_password
	
	public MyUser() {}

	public MyUser(Long id, String username, String email, String fullname, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static class MyUserMapper implements RowMapper<MyUser> {

		@Override
		public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new MyUser(rs.getLong("id"), rs.getString("username"), rs.getString("email"),
					rs.getString("fullname"), rs.getString("encrypted_password"));
		}

	}
}
