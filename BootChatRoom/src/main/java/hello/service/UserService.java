package hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import hello.entity.User;
import hello.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User findUserById(long id) {
		return userRepository.findById(id);
	}
	
	public List<User> fillAllUsers() {
		return userRepository.findAll();
	}
	
	public String saveUser(User user) {
		if(user.getSingleId() != null && user.getSingleId().trim().equals("")) {
			user.setSingleId(null);
		}
		
		// mã hóa password trước khi save vào database
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setConfirmPassword(user.getPassword());
		System.out.println("encoded pass = " + user.getPassword());
		
		try {
			userRepository.save(user);
			return "OK";
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			return "duplicate_key";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public List<String> getUserFullnameInRoom(long roomId) {
		return userRepository.getUserFullnameInRoom(roomId);
	}
	
	public List<Object[]> getUsernameAndFullname(long userId) {
		return userRepository.getUsernameAndFullname(userId);
	}
	
	public User getUsernameAndFullname_JPQL (long userId) {
		return userRepository.getUsernameAndFullname_JPQL(userId);
	}
	
	public List<Object[]> get5NewestUsernameAndFullname() {
		return userRepository.get5NewestUsernameAndFullname();
	}
	
	public List<Object[]> getCommentsOfUserInRoom(long roomId) {
		return userRepository.getCommentsOfUserInRoom(roomId);
	}
}
