package configurationProject.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import configurationProject.dao.user.UserDao;
import configurationProject.model.user.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void addUser(User u) {
		this.userDao.addUser(u);
	}

	@Override
	public void updateUser(User u) {
		this.userDao.updateUser(u);
	}

	@Override
	public void removeUser(Long id) {
		this.userDao.removeUser(id);
		
	}

	@Override
	public List<User> findAll() {
		return this.userDao.findAll();
	}
	/*@SuppressWarnings("unchecked")
	@Override
	public org.springframework.security.core.userdetails.User getUserByUsernameAndPassword(String username, String password) {
	
			User user = this.userDao.getUserByUsername(username);
			if(user != null) {
				GrantedAuthority authority = new GrantedAuthority() {
					
					private static final long serialVersionUID = 1L;
		
					@Override
					public String getAuthority() {
						return user.getUserRole().toString();
					}
				};
				org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username, password, (Collection<? extends GrantedAuthority>) authority);
				return securityUser;
			} else {
				throw new UsernameNotFoundException("User with this username and password doesn't exist");
			}
		
	}*/


	/*@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userDao.getUserByUsername(username);
		if(user != null) {
			GrantedAuthority authority = new GrantedAuthority() {
				
				private static final long serialVersionUID = 1L;
	
				@Override
				public String getAuthority() {
					return user.getUserRole().toString();
				}
			};
			org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username, user.getPassword(), (Collection<? extends GrantedAuthority>) authority);
			return securityUser;
		} else {
			throw new UsernameNotFoundException("User with this username and password doesn't exist");
		}
	}*/

	

	
	
}
