package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;

import hello.dao.MyUserAccountDAO;
import hello.model.MyUserAccount;
import hello.user.MySocialUserDetails;

/*
 * MyUserDetailsService là dịch vụ được sử dụng bởi Spring Security 
 * API để lấy thông tin người dùng từ Database
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MyUserAccountDAO myUserAccountDAO;

	public MyUserDetailsService() {

	}

	// (Phương thức này được sử dụng bởi Spring Security API).
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		MyUserAccount myUserAccount = myUserAccountDAO.findByUserName(userName);

		if (myUserAccount == null) {
			throw new UsernameNotFoundException("No user found with userName: " + userName);
		}

		// Chú ý: SocialUserDetails mở rộng từ interface UserDetails.
		SocialUserDetails principal = new MySocialUserDetails(myUserAccount);

		return principal;
	}

}