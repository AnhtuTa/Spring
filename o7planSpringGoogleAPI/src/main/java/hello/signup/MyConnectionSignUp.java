package hello.signup;

import hello.dao.MyUserAccountDAO;
import hello.model.MyUserAccount;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/*
 * Như đã giới thiệu ở phần trên. Sau khi đăng nhập thông qua mạng xã hội,
 *  nếu thông tin người dùng chưa tồn tại trên Dabase của ứng dụng,
 *  nó sẽ được tạo ra tự động hoặc chuyển hướng tới một trang cho phép 
 *  người dùng nhập thông tin tài khoản.
 * 
 * Trong trường hợp bạn muốn tự động tạo ra các bản ghi USER_ACCOUNTS & 
 *  USERCONNECTION, bạn phải viết class thực hiện interface ConnectionSignUp.
 */
public class MyConnectionSignUp implements ConnectionSignUp {

	private MyUserAccountDAO myUserAccountDAO;

	public MyConnectionSignUp(MyUserAccountDAO myUserAccountDAO) {
		this.myUserAccountDAO = myUserAccountDAO;
	}

	// Sau khi đăng nhập mạng xã hội xong.
	// Phương thức này sẽ được gọi để tạo ra một bản ghi User_Account tương ứng
	// nếu nó chưa tồn tại.
	@Override
	public String execute(Connection<?> connection) {

		MyUserAccount account = myUserAccountDAO.createUserAccount(connection);
		return account.getId();
	}

}