package configurationProject.service.component;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import configurationProject.dao.component.ComponentDao;
import configurationProject.model.component.Component;

@Service
@Transactional
public class ComponentServiceImpl implements ComponentService {

	private ComponentDao componentDao;
	
	@Autowired
	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	@Override
	public List<Component> findAll() {
		return this.componentDao.listOfComponents();
	}

	@Override
	public void save(Component component) {
		this.componentDao.addComponent(component);
	}

	@Override
	public void deleteComponent(Long id) {
		this.componentDao.removeComponent(id);
	}

	@Override
	public Component findComponentById(Long id) {
		return this.componentDao.getComponentById(id);
	}

	@Override
	public void updateComponent(Component component) {
		this.componentDao.updateComponent(component);		
	}

	@Override
	public List<Component> sortBy(String sorter, String description) {
		return this.componentDao.sortBy(sorter, description);
	}




}
