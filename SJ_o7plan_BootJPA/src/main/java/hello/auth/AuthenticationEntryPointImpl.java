package hello.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/*
 * Lớp AuthenticationEntryPointImpl mở rộng (extends) từ lớp 
 * BasicAuthenticationEntryPoint, nó được sử dụng để kiểm tra 
 * username/password đính kèm theo request có hợp lệ hay không.
 */
@Component
public class AuthenticationEntryPointImpl extends BasicAuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, //
			AuthenticationException authEx) throws IOException, ServletException {
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 - " + authEx.getMessage());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// RealmName xuất hiện trên cửa sổ đăng nhập (Firefox).
		setRealmName("This is realm name: o7planning");
		super.afterPropertiesSet();
	}
}
