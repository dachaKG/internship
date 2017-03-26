package configurationProject.service.typeOfComponent;

import java.util.List;

import configurationProject.model.typeOfComponent.TypeOfComponent;

public interface TypeOfComponentService {

	public List<TypeOfComponent> findAll();
	
	public void addTypeOfComponent(TypeOfComponent typeOfComponent);
	
	public void updateTypeOfComponent(TypeOfComponent typeOfComponent);
	
	public TypeOfComponent findTypeOfComponentById(Long id);
	
	public void delete(Long id);
	
	public List<TypeOfComponent> sortBy(String sorter, String descriptior);
	
	public TypeOfComponent findTypeOfComponentByName(String name);
	
}
