package hello.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * Class này chỉ phục vụ cho security, login, chứ không liên quan đến bảng user trong database
 * @author tu.ta1
 *
 */
public class MyUserDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = -7681259870674926202L;
	
	private long id;
	private String fullname;
	
	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> //
			authorities, long userId, String fullname) {
		super(username, password, authorities);
		this.id = userId;
		this.fullname = fullname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
