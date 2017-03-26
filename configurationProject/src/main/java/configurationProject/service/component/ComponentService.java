package configurationProject.service.component;

import java.util.List;

import configurationProject.model.component.Component;


public interface ComponentService {

	public List<Component> findAll();
	
	public void save(Component component);
	
	public void updateComponent(Component component);
	
	public void deleteComponent(Long id);
	
	public Component findComponentById(Long id);
	
	public List<Component> sortBy(String sorter, String description);

	
}
