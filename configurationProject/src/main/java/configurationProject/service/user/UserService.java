package configurationProject.service.user;

import java.util.List;

import configurationProject.model.user.User;

public interface UserService {

	public void addUser(User u);
	
	public void updateUser(User u);
		
	//public org.springframework.security.core.userdetails.User getUserByUsernameAndPassword(String userName, String password);

	void removeUser(Long id);
	
	public List<User> findAll();
	
}
