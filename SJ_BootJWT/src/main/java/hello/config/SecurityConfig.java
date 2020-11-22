package hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import hello.filter.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private UserDetailsService userDetailsService;

//	@Bean
//	public RestAuthenticationEntryPoint restServicesEntryPoint() {
//		return new RestAuthenticationEntryPoint();
//	}
//
//	@Bean
//	public CustomAccessDeniedHandler customAccessDeniedHandler() {
//		return new CustomAccessDeniedHandler();
//	}

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Spring boot 2.x dùng BCryptPasswordEncoder bị lỗi
        // return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }
	
	/**
	 * Filter sẽ được thực thi với tất cả các endpoint được config bởi HttpSecurity.
	 * Do đó muốn exclude endpoint nào thì phải dùng WebSecurity
	 * 
	 * Ref: https://stackoverflow.com/a/38960531/7688028
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
	    // Ignore login, swagger endpoints
	    web.ignoring().antMatchers(
	            "/login",
	            "/logout",
	            "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        //bean jwtAuthenticationTokenFilter sẽ thực hiện việc xác thực người dùng
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        
	    // Nếu ko disable csrf thì trên swagger sẽ báo lỗi:
	    // Could not verify the provided CSRF token because your session was not found
		http.csrf().disable()
		    .anonymous().and()
            .authorizeRequests()
            .antMatchers("/login",
                    "/logout",
                    "/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**").permitAll()
            .anyRequest().authenticated();

		//restServicesEntryPoint() sẽ xử lý những request chưa được xác thực.
//		http.authorizeRequests().antMatchers("/rest/**").authenticated().and()

		//Các url /rest/** với method GET (API lấy thông tin user) 
				//cho phép cả role ADMIN và USER truy cập, với các method 
				//“DELETE” và “POST” (xóa và tạo mới user) thì chỉ cho phép role ADMIN truy cập.
//				.antMatchers(HttpMethod.GET, "/rest/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//				.antMatchers(HttpMethod.POST, "/rest/**").access("hasRole('ROLE_ADMIN')")
//				.antMatchers(HttpMethod.DELETE, "/rest/**").access("hasRole('ROLE_ADMIN')").and()

				//trường hợp người dùng gửi request mà không có quyền sẽ do bean 
				//customAccessDeniedHandlerxử lý (Ví dụ role USER nhưng gửi request xóa user)
				//.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
	}
}
