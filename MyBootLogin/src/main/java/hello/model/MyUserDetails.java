//package hello.model;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
///*
// * Trong project này thì class này ko dùng, vì ko biết cách dùng :(
// */
//public class MyUserDetails extends MyUser implements UserDetails {
//	private static final long serialVersionUID = -5385834150081356895L;
//	private List<GrantedAuthority> grantList;
//	
//	public MyUserDetails(Long userId, String userName, String fullName, String encrytedPassword,
//			List<GrantedAuthority> grantList) {
//		super(userId, userName, fullName, encrytedPassword);
//		this.grantList = grantList;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.grantList;
//	}
//
//	@Override
//	public String getPassword() {
//		return this.getEncrytedPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return this.getUserName();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//}
