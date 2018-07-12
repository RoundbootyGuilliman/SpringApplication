package app.service.impl;

import app.dao.UserDAO;
import app.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		
		app.entity.User user = userDAO.getByEmail(email);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
		
		return buildUserForAuthentication(user, authorities);
	}
	
	private User buildUserForAuthentication(app.entity.User user, List<GrantedAuthority> authorities) {
		
		boolean isEnabled = true;
		boolean isAccountNonExpired = true;
		boolean isCredentialsNonExpired = true;
		boolean isAccountNonLocked = true;
		
		return new User(user.getUsername(), user.getPassword(), isEnabled, isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(List<Role> roles) {
		
		List<GrantedAuthority> result = new ArrayList<>();
		
		for (Role role : roles) {
			result.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		}
		return result;
	}
}