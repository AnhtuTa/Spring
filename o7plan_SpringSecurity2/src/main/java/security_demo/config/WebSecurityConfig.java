package security_demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import security_demo.service.SpitterDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	SpitterDetailsService spitterDetailsService;

	@Autowired
	DataSource dataSource;

	// Khác gì so với hàm này: configureGlobal
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Các User trong bộ nhớ (IN-MEMORY).
		auth.inMemoryAuthentication().withUser("demo").password("1111").roles("USER").and().withUser("att")
				.password("1111").authorities("ROLE_USER", "ROLE_ADMIN");

		// Authenticating against database tables
		// KHÔNG LÀM THEO CÁCH NÀY

		// Configuring a custom user service
		auth.userDetailsService(spitterDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// 9.3 Intercepting requests
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// Các trang không yêu cầu login
		http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();

		// Trang /userInfo yêu cầu phải login với vai trò USER hoặc ADMIN.
		// Nếu chưa login, nó sẽ redirect tới trang /login.
		http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_WTF')");

		// For ADMIN only.
		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// 9.4 Authenticating users
		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()	//
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login").defaultSuccessUrl("/userInfo") //
				.failureUrl("/login?error=true") //
				.usernameParameter("username")
				.passwordParameter("password")

				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	}

	//@Bean(name="passwordEncoder")
	// Chắc ko cần annotation @Bean
//	public PasswordEncoder pwEncorder() {
//		return new BCryptPasswordEncoder();
//	}

	// Just a demo
	public static void main(String[] args) {
		String str = new BCryptPasswordEncoder().encode("1111");
		System.out.println(str);
	}
}
