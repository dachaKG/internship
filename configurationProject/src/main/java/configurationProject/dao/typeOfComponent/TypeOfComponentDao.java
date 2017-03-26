package configurationProject.dao.typeOfComponent;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import configurationProject.model.typeOfComponent.TypeOfComponent;

@Repository("typeOfComponentDao")
@Transactional
public class TypeOfComponentDao {
	

    private EntityManager manager;
	
    @PersistenceContext
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void addTypeOfComponent(TypeOfComponent t){
		manager.persist(t);
	}

	@SuppressWarnings("unchecked")
	public List<TypeOfComponent> listOfTypes(){
		return manager.createQuery("Select t from TypeOfComponent t").getResultList();
	}
	
	public void updateTypeOfComponent(TypeOfComponent t){
		manager.merge(t);
	}

	public TypeOfComponent getTypeOfComponentById(Long id){
		return (TypeOfComponent) manager.createQuery("Select t from TypeOfComponent t where t.id = " + id).getSingleResult();
	}
	
	public TypeOfComponent getTypeOfComponentByName(String name){
		return (TypeOfComponent) manager.createQuery("Select t from TypeOfComponent t where t.name = '" + name + "'").getSingleResult();
		
	}
	
	public void removeType(Long id){
		manager.remove(manager.find(TypeOfComponent.class, id));
	}
	
	@SuppressWarnings("unchecked")
	public List<TypeOfComponent> sortBy(String sorter, String descriptior){
		List<TypeOfComponent> typeOfComponentList = manager.createQuery("Select t from TypeOfComponent t order by " + sorter + " " + descriptior).getResultList();
		return typeOfComponentList;
	}

}
