package hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Các User trong bộ nhớ (IN-MEMORY).
		auth.inMemoryAuthentication()
			.withUser("huy").password("$2a$10$BzRjkOMqYdoVfirYJh/SV.ItUrbpMFwIcEoY.Cfpw1h7V4S4JiiXS").roles("USER")
			.and()
			.withUser("att").password("$2a$10$BzRjkOMqYdoVfirYJh/SV.ItUrbpMFwIcEoY.Cfpw1h7V4S4JiiXS").authorities("ROLE_USER", "ROLE_ADMIN");
	}
	
	@Bean(name="passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		// Trang /userInfo yêu cầu phải login với vai trò USER hoặc ADMIN.
		// Nếu chưa login, nó sẽ redirect tới trang /login.
		http.authorizeRequests().antMatchers("/user**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

		// For ADMIN only.
		http.authorizeRequests().antMatchers("/admin**").access("hasRole('ROLE_ADMIN')");

		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()	//
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				//Vì user chưa đăng nhập nên Spring Security sẽ tự động chuyển hướng ta tới 
				//trang đăng nhập. (Ở đây mình không tạo trang đăng nhập nên Spring Security 
				//sẽ chuyển tới trang đăng nhập mặc định của nó)
				//.loginPage("/login").defaultSuccessUrl("/userInfo") //
				.failureUrl("/login?error=true") //
				.usernameParameter("username")
				.passwordParameter("password")

				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	}

	// Just a demo
	public static void main(String[] args) {
		String str = new BCryptPasswordEncoder().encode("1111");
		System.out.println(str);
	}
}
