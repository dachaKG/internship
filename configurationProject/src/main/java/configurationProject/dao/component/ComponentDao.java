package configurationProject.dao.component;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import configurationProject.model.component.Component;

@Repository("componentDao")
@Transactional
public class ComponentDao {


	@PersistenceContext
    private EntityManager manager;
	
	
	public void addComponent(Component c){
		manager.persist(c);
	}

	public void updateComponent(Component c){
		manager.merge(c);
	}
	
	public void removeComponent(Long id) {
		manager.remove(manager.find(Component.class, id));
		
	}

	@SuppressWarnings("unchecked")
	public List<Component> listOfComponents(){
		return manager.createQuery("Select c from Component c").getResultList();
	}

	public Component getComponentById(Long id){
		return (Component) manager.createQuery("Select c from Component c where c.id = " + id).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Component> sortBy(String sorter, String descriptior){
		List<Component> componentList = manager.createQuery("Select c from Component c order by " + sorter + " " + descriptior).getResultList();
		return componentList;
	}
}
