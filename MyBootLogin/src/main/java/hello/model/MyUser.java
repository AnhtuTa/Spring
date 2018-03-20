package hello.model;

/*
 * Lớp MyUser đại diện cho một bản ghi trong 
 * bảng APP_USER của database.
 */
public class MyUser {
	private Long userId;
	private String userName;
	private String fullName;
	private String encrytedPassword;

	public MyUser() {

	}

	public MyUser(Long userId, String userName, String fullName, String encrytedPassword) {
		this.userId = userId;
		this.userName = userName;
		this.fullName = fullName;
		this.encrytedPassword = encrytedPassword;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEncrytedPassword() {
		return encrytedPassword;
	}

	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = encrytedPassword;
	}

	@Override
	public String toString() {
		return this.userName + " - " + this.fullName;
	}
}
