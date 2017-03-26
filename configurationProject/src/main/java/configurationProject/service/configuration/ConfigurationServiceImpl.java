package configurationProject.service.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import configurationProject.dao.configuration.ConfigurationDao;
import configurationProject.model.configuration.Configuration;

@Service
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService{
	
	private ConfigurationDao configurationDao;
	
	@Autowired
	public void setConfigurationDao(ConfigurationDao configurationDao) {
		this.configurationDao = configurationDao;
	}

	@Override
	public void save(Configuration configuration) {
		this.configurationDao.addConfiguration(configuration);
		
	}

	@Override
	public List<Configuration> findAll() {
		return this.configurationDao.listOfConfigurations();
	}

	@Override
	public Configuration getConfigurationById(Long id) {
		return this.configurationDao.getConfigurationById(id);
	}

}
