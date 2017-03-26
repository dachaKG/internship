package configurationProject.dao.configuration;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import configurationProject.model.configuration.Configuration;

@Repository("configurationDao")
@Transactional
public class ConfigurationDao {

	@PersistenceContext
    private EntityManager manager;
	

	public void addConfiguration(Configuration c) {
		manager.persist(c);
	}
	
	@SuppressWarnings("unchecked")
	public List<Configuration> listOfConfigurations() {
		return manager.createQuery("Select c from Configuration c").getResultList();
		
	}
	
	public Configuration getConfigurationById(Long id){
		return (Configuration) manager.createQuery("Select c from Configuration c where c.id = " + id).getSingleResult();
	}

	
}
