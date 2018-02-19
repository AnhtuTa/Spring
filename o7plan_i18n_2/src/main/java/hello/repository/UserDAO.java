package hello.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	public boolean checkUser(String user, String pass) {
		return "att".equals(user) && "1".equals(pass);
	}
}
