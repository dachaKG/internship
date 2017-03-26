package configurationProject.service.typeOfComponent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import configurationProject.dao.typeOfComponent.TypeOfComponentDao;
import configurationProject.model.typeOfComponent.TypeOfComponent;

@Service
@Transactional
public class TypeOfComponentServiceImpl implements TypeOfComponentService{

	
	private TypeOfComponentDao typeOfComponentDao;
	
	
	@Autowired
	public void setTypeOfComponentDao(TypeOfComponentDao typeOfComponentDao) {
		this.typeOfComponentDao = typeOfComponentDao;
	}

	@Override
	public List<TypeOfComponent> findAll() {
		return this.typeOfComponentDao.listOfTypes();
	}

	@Override
	public void addTypeOfComponent(TypeOfComponent typeOfComponent) {
		this.typeOfComponentDao.addTypeOfComponent(typeOfComponent);
	}

	@Override
	public TypeOfComponent findTypeOfComponentById(Long id) {
		return this.typeOfComponentDao.getTypeOfComponentById(id);
	}

	@Override
	public void updateTypeOfComponent(TypeOfComponent typeOfComponent) {
		this.typeOfComponentDao.updateTypeOfComponent(typeOfComponent);
	}

	@Override
	public void delete(Long id) {
		this.typeOfComponentDao.removeType(id);
		
	}

	@Override
	public List<TypeOfComponent> sortBy(String sorter, String descriptior) {
		return this.typeOfComponentDao.sortBy(sorter, descriptior);
	}

	@Override
	public TypeOfComponent findTypeOfComponentByName(String name) {
		return this.typeOfComponentDao.getTypeOfComponentByName(name);
	}


}
