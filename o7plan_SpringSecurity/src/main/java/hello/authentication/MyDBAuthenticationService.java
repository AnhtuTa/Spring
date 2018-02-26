package hello.authentication;

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

import hello.dao.UserInfoDAO;
import hello.model.UserInfo;

@Service
public class MyDBAuthenticationService implements UserDetailsService {

	@Autowired
	private UserInfoDAO userInfoDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoDAO.findUserInfo(username);
		System.out.println("UserInfo= " + userInfo);

		if (userInfo == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		// lấy role theo username. roles có thể = [USER,ADMIN,..]
		List<String> roles = userInfoDAO.getUserRoles(username);

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roles != null) {
			for (String role : roles) {
				// ROLE_USER, ROLE_ADMIN,.. Chú ý: các giá trị trong cột USER_ROLE của bảng user_roles sẽ KHÔNG có tiền tố ROLE_ 
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);	// Do đó cần thêm tiền tố ROLE_
				// vì trong hàm configure(HttpSecurity http) ta phân quyền bằng các giá trị có tiền tố đó
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(), //
				userInfo.getPassword(), grantList);

		return userDetails;
	}

}