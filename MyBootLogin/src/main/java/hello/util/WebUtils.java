package hello.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class WebUtils {
	/*
	 * VD kq trả về: UserName:admin (ROLE_ADMIN, ROLE_USER
	 */
	public static String toString(User user) {
		StringBuilder sb = new StringBuilder();

		sb.append("UserName:").append(user.getUsername());

		Collection<GrantedAuthority> authorities = user.getAuthorities();
		if (authorities != null && !authorities.isEmpty()) {
			sb.append(" (");
			boolean first = true;
			for (GrantedAuthority a : authorities) {
				if (first) {
					sb.append(a.getAuthority());
					first = false;
				} else {
					sb.append(", ").append(a.getAuthority());
				}
			}
			sb.append(")");
		}
		System.out.println("[WebUtils] sb = " + sb.toString());
		return sb.toString();
	}
}
