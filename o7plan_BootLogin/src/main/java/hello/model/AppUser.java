package hello.model;

/*
 * Lớp AppUser đại diện cho một bản ghi trong 
 * bảng APP_USER của database.
 */
public class AppUser {
	private Long userId;
	private String userName;
	private String encryptedPassword;

	public AppUser() {

	}

	public AppUser(Long userId, String userName, String encryptedPassword) {
		this.userId = userId;
		this.userName = userName;
		this.encryptedPassword = encryptedPassword;
	}

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	@Override
	public String toString() {
		return this.userName + "/" + this.encryptedPassword;
	}
}
