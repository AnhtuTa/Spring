package hello.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hello.entity.MyUserDetails;
import hello.entity.Role;
import hello.entity.User;

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
 * 
 * Từ nay, nếu viết là User thì ám chỉ class hello.entity.User
 * còn User của spring thì viết rõ cả package: org.springframework.security.core.userdetails.User
 */
@Transactional
@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	private EntityManager entityManager;

	/*
	 * Tham số truyền vào chỉ gồm có username của người dùng. Ta sẽ tìm kiếm trong
	 * CSDL, record thỏa mãn username. Nếu không tìm thấy, ta sẽ ném ra ngoại lệ
	 * UsernameNotFoundException.
	 * 
	 * Phương thức loadUserByUsername() sẽ trả về một implementation của UserDetails
	 * Implementation ở đây có thể là:
	 * org.springframework.security.core.userdetails.User, YourCustomUserDetails
	 * implements org.springframework.security.userdetails.UserDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("============ UserDetailsServiceImp.loadUserByUsername() =============");
		// User user = userService.findUserByUsername(username);
		// Dùng lệnh trên sẽ ko load được role của user vì
		// fetch = FetchType.LAZY ở chỗ này ko chạy được, do session bị close
		
		String sql = "Select u from " + User.class.getName() + " u " //
				+ " where u.username = :username";
		TypedQuery<User> query = entityManager.createQuery(sql, User.class);
		query.setParameter("username", username);
		
		List<User> userList = query.getResultList();
		if (userList.size() == 0) {
			System.out.println("[UserDetailsServiceImp] Không tồn tại user nào!");
			throw new UsernameNotFoundException("User " + username + " was not found in the DB");
		}

		User user = userList.get(0);
		Set<Role> roles = user.getRoles();
		List<GrantedAuthority> grantList = new ArrayList<>();
		if (roles != null && roles.size() != 0) {
			for (Role role : roles) {
				grantList.add(new SimpleGrantedAuthority(role.getName()));
			}
		} else {
			System.out.println("[UserDetailsServiceImp] This user doesn't any role!");
		}

		System.out.println("============ end of UserDetailsServiceImp.loadUserByUsername() =============");
		return new MyUserDetails(user.getUsername(), user.getPassword(), 
				grantList, user.getId(), user.getFullname());
	}

}
