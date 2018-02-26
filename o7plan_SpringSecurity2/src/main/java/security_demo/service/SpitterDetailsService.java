package security_demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import security_demo.dao.SpitterDetailsDAO;
import security_demo.model.Spitter;

@Service
public class SpitterDetailsService implements UserDetailsService {

	@Autowired
	SpitterDetailsDAO spitterDetailsDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Spitter s = spitterDetailsDAO.findSpitter(username);
		if(s == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
		List<String> roles = spitterDetailsDAO.getSpitterRoles(username);
		List<GrantedAuthority> grantList = new ArrayList<>();
		if(roles.size() != 0) {
			for(String role : roles) {
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		
		return new User(s.getUsername(), s.getPassword(), grantList);
	}

}
