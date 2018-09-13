package hello.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import hello.dao.AppUserDAO;
import hello.entity.AppUser;

/*
 * Trong lần đầu tiên người dùng đăng nhập với tài khoản mạng xã hội
 * của họ, ứng dụng sẽ có được đối tượng Connection, đối tượng này 
 * chứa hồ sơ ( Profile) của người dùng. Bạn sẽ viết code trong lớp 
 * ConnectionSignUpImpl để tạo ra một bản ghi của bảng APP_USER. 
 * (Xem thêm code trong lớp SocialConfig).
 */
public class ConnectionSignUpImpl implements ConnectionSignUp {
	private AppUserDAO appUserDAO;

	public ConnectionSignUpImpl(AppUserDAO appUserDAO) {
		this.appUserDAO = appUserDAO;
	}

	// After logging in social networking.
	// This method will be called to create a corresponding App_User record
	// if it does not already exist.
	@Override
	public String execute(Connection<?> connection) {

		AppUser account = appUserDAO.createAppUser(connection);
		return account.getUserName();
	}
}
