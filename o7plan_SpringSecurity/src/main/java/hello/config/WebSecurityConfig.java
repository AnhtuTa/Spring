package hello.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import hello.authentication.MyDBAuthenticationService;

@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyDBAuthenticationService myDBAauthenticationService;
	
	@Autowired
	DataSource dataSource;

	/*
	 * Chú ý: các comment = tiếng anh đc lấy trong cuốn Spring In Action
	 * (chapter 9: Securing web applications)
	 * Có 4 cách để config 1 configuration store bằng việc sử dụng 4 hàm sau:
	 * - jdbcAuthentication(),
	 * - ldapAuthentication,
	 * - inMemoryAuthentication()
	 * - userDetailsService()
	 */
	// 9.2 Selecting user details services
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Các User trong bộ nhớ (IN-MEMORY).
		auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
		auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER, ADMIN")
			.and().withUser("user2").password("1111").roles("USER")	// dùng hàm and() để chain together multiple user configurations. 
			.and().withUser("user3").password("1111").authorities("ROLE_USER");
		// Note that the roles() method is a shortcut for the authorities() methods. 
		// Any values given to roles() are prefixed with ROLE_ and assigned
		// as authorities to the user
		/*
		 * Although an in-memory user store is very useful for debugging and developer testing
			purposes, it’s probably not the most ideal choice for a production application. For
			production-ready purposes, it’s usually better to maintain user data in a database of
			some sort.
		 */
		
		// Authenticating against database tables:
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT username, password, enabled FROM spitter WHERE username=?")
			.authoritiesByUsernameQuery("SELECT username, role FROM spitter_roles WHERE username=?")
			//.passwordEncoder(new StandardPasswordEncoder("whatthehell"));		//ko biết dùng cái này
			.passwordEncoder(new Md5PasswordEncoder());		// Băm mật khẩu người dùng nhập vào, sau đó
			// so sánh nó với mật khẩu trong CSDL
		
		// Applying LDAP-backed authentication
//		auth.ldapAuthentication()
//			.userSearchBase("ou=people")	//organizational unit
//			.userSearchFilter("uid={0})")
//			.groupSearchBase("ou=groups")
//			.groupSearchFilter("member={0}")
//			.passwordCompare()
//			.passwordEncoder(new Md5PasswordEncoder())
//			.passwordAttribute("password");
		
		// REFERRING TO A REMOTE LDAP SERVER
//		auth.ldapAuthentication()
//			.userSearchBase("ou=people")	//organizational unit
//			.userSearchFilter("uid={0})")
//			.groupSearchBase("ou=groups")
//			.groupSearchFilter("member={0}")
//			.contextSource();
		
		
		// CONFIGURING AN EMBEDDED LDAP SERVER
//		auth.ldapAuthentication()
//			.userSearchBase("ou=people")
//			.userSearchFilter("(uid={0})")
//			.groupSearchBase("ou=groups")
//			.groupSearchFilter("member={0}")
//			.contextSource()
//			.root("dc=habuma,dc=com")
//			.ldif("classpath:users.ldif");
		

		// Configuring a custom user service
		// Các User trong Database
		// Sẽ ko dùng phần sau đâu, vì password trong CSDL đc băm = MD5,
		// mà myDBAauthenticationService ko có chỗ nào find user mà password
		// có mã hóa = MD5 cả! (Nó chỉ tìm kiếm pass = pass người dùng nhập vào)
		auth.userDetailsService(myDBAauthenticationService);
	}

	// 9.3 Intercepting requests
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// Có thể sử dụng regexMatcher, chẳng hạn như:
		http.authorizeRequests().regexMatchers("/welcome/wtf/.*", "/welcome/[abc]{1,3}/.+", "/welcome/hehe/123").hasRole("WTF");
		
		// Các trang không yêu cầu login
		http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();
		
		// Trang /userInfo yêu cầu phải login với vai trò USER hoặc ADMIN.
		// Nếu chưa login, nó sẽ redirect tới trang /login.
		http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

		// For ADMIN only.
		// Trang chỉ dành cho ADMIN (hasRole('ROLE_ADMIN'))
		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// 9.4 Authenticating users
		/*
		 sau khi ấn submit ở trang login thì Spring sẽ xử lý ở trang
		 /j_spring_security_check. Trang này ta ko viết code xử lý mà
		 Spring sẽ xử lý thông qua các config ở configureGlobal(AuthenticationManagerBuilder auth)
		 */
		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()
				// Submit URL của trang login
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/userInfo")//
				.failureUrl("/login?error=true")//
				.usernameParameter("username")//
				.passwordParameter("password")

				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
		
		// Require HTTPS for login page
//		http.requiresChannel()
//		.antMatchers("/login")
//		.requiresSecure();

	}
}