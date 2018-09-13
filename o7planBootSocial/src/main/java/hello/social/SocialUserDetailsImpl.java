package hello.social;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import hello.entity.AppUser;

public class SocialUserDetailsImpl implements SocialUserDetails {

	private static final long serialVersionUID = -6201676254586099802L;
	private AppUser appUser;
	private List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

	public SocialUserDetailsImpl(AppUser appUser, List<String> roleNames) {
		this.appUser = appUser;
		
		for(String role : roleNames) {
			this.grantList.add(new SimpleGrantedAuthority(role));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantList;
	}

	@Override
	public String getPassword() {
		return appUser.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		return appUser.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUserId() {
		return this.appUser.getUserId() + "";
	}

}
