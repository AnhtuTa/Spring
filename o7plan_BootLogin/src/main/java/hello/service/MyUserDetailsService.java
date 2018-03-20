package hello.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hello.dao.AppRoleDAO;
import hello.dao.AppUserDAO;
import hello.model.AppUser;

/*
 * UserDetailsService là một interface trung tâm trong Spring Security. 
 * Nó là một dịch vụ để tìm kiếm "Tài khoản người dùng và các vai trò của 
 * người dùng đó". Nó được sử dụng bởi Spring Security mỗi lần người dùng 
 * đăng nhập vào hệ thống. Chính vì vậy bạn cần viết một lớp thi hành 
 * (implements) interface này.
 * 
 * Ở đây tôi tạo lớp MyUserDetailsService thi hành (implements) interface 
 * UserDetailsService. Lớp  MyUserDetailsService được chú thích bởi 
 * @Service để nói với Spring rằng hãy quản lý nó như một Spring BEAN
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserDAO appUserDAO;

	@Autowired
	private AppRoleDAO appRoleDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AppUser appUser = appUserDAO.findUserAccount(userName);
		
		if(appUser == null) {
			throw new UsernameNotFoundException("User " + userName + " was not found in the DB");
		}
		
		List<String> roles = appRoleDAO.getRoleNames(appUser.getUserId());
		List<GrantedAuthority> grantList = new ArrayList<>();
		if(roles != null && roles.size() != 0) {
			for(String role : roles) {
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		
		return (UserDetails) new User(appUser.getUserName(), appUser.getEncrytedPassword(),
				grantList);
	}

}
