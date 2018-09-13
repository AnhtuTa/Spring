package hello.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/*
 * Bảng APP_USER, APP_ROLE, USER_ROLE sẽ được tự động tạo ra dựa trên 
 * các lớp AppUser, AppRole, UserRole
 */

@Entity
@Table(
	name = "app_user", 
	uniqueConstraints = { 
		@UniqueConstraint(name = "APP_USER_UK", columnNames = "USER_NAME"),
		@UniqueConstraint(name = "APP_USER_UK2", columnNames = "EMAIL") 
	}
)
public class AppUser {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID", nullable = false)
	private Long userId;

	@Column(name = "USER_NAME", length = 36, nullable = false)
	private String userName;

	@Column(name = "EMAIL", length = 128, nullable = false)
	private String email;

	@Column(name = "FIRST_NAME", length = 36, nullable = true)
	private String firstName;

	@Column(name = "LAST_NAME", length = 36, nullable = true)
	private String lastName;

	@Column(name = "ENCRYPTED_PASSWORD", length = 128, nullable = false)
	private String encryptedPassword;

	@Column(name = "ENABLED", length = 1, nullable = false)
	private boolean enabled;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
