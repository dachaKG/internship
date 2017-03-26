package configurationProject.service.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import configurationProject.dao.user.UserDao;
import configurationProject.model.user.User;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userDao.getUserByUsername(username);
		if(user != null) {
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new GrantedAuthority() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = -1345017020248096210L;

				@Override
				public String getAuthority() {
					return user.getUserRole().toString();
				}
			});
			org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
			return securityUser;
		} else {
			throw new UsernameNotFoundException("User with this username and password doesn't exist");
		}
	}

}
