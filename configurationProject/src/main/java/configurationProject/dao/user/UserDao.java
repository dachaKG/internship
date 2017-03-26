package configurationProject.dao.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import configurationProject.model.user.User;

@Repository("userDao")
@Transactional
public class UserDao {

	
	@PersistenceContext
    private EntityManager manager;
	
	public void addUser(User u){
		manager.persist(u);
	}
	
	public void updateUser(User u){
		manager.merge(u);
	}
	
	public void removeUser(Long id){
		
		manager.remove(manager.find(User.class, id));
	}
	
	public User getUserByUsername(String username) throws NoResultException{
		
		return (User) manager.createQuery("Select u from User u where username = '" + username + "'").getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAll(){
		return manager.createQuery("Select u from User u").getResultList();
	}
	
}
