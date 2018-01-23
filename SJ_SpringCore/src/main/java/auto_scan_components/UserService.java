package auto_scan_components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Lưu ý, mặc định bean được tạo từ auto scan component có 
//tên là tên của class với chữ đầu tiên viết thường.
//Ví dụ: UserService -> userService, UserDAO -> userDAO
//nếu dưới đây chỉ để annotation là @Service thì tên của bean 
//sẽ là userService.

@Service("service_what_the_hell")
public class UserService {
	@Autowired
	UserDAO userDAO;

	public void findUser(int id) {
		System.out.println("userService find:");
		userDAO.findUser(id);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
