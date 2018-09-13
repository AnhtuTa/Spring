package demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import demo.concurrent_session_controller.CustomAuthenticationFailureHandler;
import demo.login_remember_me.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Có 2 cách khởi tạo 1 bean:
	// 1. Tạo 1 method có kiểu dữ liệu là been cần tạo và thêm annotation @Bean
	// 2. Tạo 1 field có kiểu dữ liệu là been cần tạo và thêm annotation @Autowired, cộng
	// với việc class been cần tạo phải có annotation: @Component, @Service hoặc @Repository,...
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	// Bean httpSessionEventPublisher để enable khả năng hỗ trợ concurrent session-control
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
			.withUser("admin").password("$2a$10$UPaj80EC/CZg6.m6o3HrLeHQuk3su2VTrO1p.fnX0AhDFgCNHo7sy")
			.roles("ADMIN").and()	//pass = 1111
			.withUser("anhtu").password("$2a$10$UPaj80EC/CZg6.m6o3HrLeHQuk3su2VTrO1p.fnX0AhDFgCNHo7sy")
			.roles("USER");
	}

	//nếu login và chọn remember-me với role = user, sau đó restart app
	//thì lúc vào /admin nó vẫn redirect tới /login.
	// Thậm chí nó còn ko vào controller /admin nữa! WHY WHY WHY WHY??
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Tại sao ko nên disable CSRF:
		// http://www.baeldung.com/spring-security-csrf
		// http.csrf().disable();
		// Sau khi disable CSRF thì trang /login và /logout (và hình như tất cả các form)
		// sẽ cần gửi kèm token csrf để có thể hoạt động đc (xem file _menu.jsp)
		
		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
		
		// Chỉ cho phép user đã đăng nhập mới được truy cập đường dẫn /profile/**
		http.authorizeRequests().antMatchers("/profile/**").authenticated();

		// Tất cả các trang chỉ dành cho ADMIN
		// Nếu chưa login, nó cũng redirect tới trang /login.
		http.authorizeRequests().antMatchers("/admin/**")
				.access("hasRole('ROLE_ADMIN')");
		
		// Cấu hình remember me:
		// - method key() xác định key để mã hóa cookie được ghi ở browser 
		//   (mặc định sẽ được generate ngẫu nhiên), method tokenValiditySeconds() sẽ xác 
		//   định thời gian tồn tại cookies (thời gian tự động login có hiệu lực)
		// - Ở trang login ta sẽ thêm một ô checkbox với name là remember-me (xem login.jsp)
		// - Lưu ý, sau khi logout hoặc thay đổi password thì token remember-me sẽ không 
		// còn tác dụng, ta phải login với remember me lại.
		http.rememberMe().key("anySecretKeyYouWant").tokenValiditySeconds(864000);	//one day

		// Cấu hình concurrent session
		// - Method sessionManagement() dùng để quản lý session,
		// - Method invalidSessionUrl() dùng để chỉ định url sẽ chuyển hướng tới nếu 
		// request chứa session đã hết hạn (CHƯA HIỂU KHI NÀO SESSION SẼ HẾT HẠN)
		// (Nếu ko login hoặc login mà ko chọn remember-me, sau đó restart lại app thì sẽ xảy ra trường hợp này)
		// - Method maximumSessions(): xác định số lượng session lớn nhất có thể hoạt động đồng thời,
		// ở đây mình để là 1 tức là 1 tài khoản chỉ cho phép hoạt động tại một nơi duy nhất.
		// - Method maxSessionsPreventsLogin(): nếu tham số đầu vào là true thì không thể login 
		// ở nơi khác khi đã đạt max session, nếu bằng false thì cho phép login ở nơi khác 
		// còn nơi login trước đó sẽ bị hết hạn.
		// - Method expiredUrl(): chỉ định đường dẫn sẽ chuyển hướng trong trường hợp login 
		// thất bại do tình huống bị timeout do login ở nơi khác
		http.sessionManagement()//.sessionFixation().newSession()
			.invalidSessionUrl("/home?message=timeout").maximumSessions(1)
			.expiredUrl("/login?message=max_session").maxSessionsPreventsLogin(true);
		// Hiện tại ví dụ này đang bị session timeout mãi, không login được
		// Hãy thử chạy = tab ẩn danh!

		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling()
			//.accessDeniedPage("/403");
			.accessDeniedHandler(accessDeniedHandler());

		// Cấu hình cho Login Form.
		// CHÚ Ý: sau khi login thành công thì trang web sẽ đi tới trang trước khi người
		// dùng vào trang login. Nếu ko tồn tại trang ý thì nó sẽ đi tới /profile (định
		// nghĩa ở dưới)
		http.authorizeRequests().and().formLogin()//
				// Submit URL của trang login
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/profile")//
				//.failureUrl("/login?error=true")//
				.failureHandler(customAuthenticationFailureHandler)
				.usernameParameter("username")//
				.passwordParameter("password")
				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logout_successful")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");	//Tại sao nó đéo delete cái cookie này?

	}

	// Just for testing BCryptPasswordEncoder
//	public static void main(String[] args) {
//		String password = "1111";
//		String encrytedPassword = new BCryptPasswordEncoder().encode(password);
//
//		System.out.println("Encrypted Password: " + encrytedPassword)
		//$2a$10$UPaj80EC/CZg6.m6o3HrLeHQuk3su2VTrO1p.fnX0AhDFgCNHo7sy
//	}
}
