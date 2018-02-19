package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.repository.UserDAO;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	
	public boolean checkLogin(String username, String password) {
		return userDAO.checkUser(username, password);
	}

}
