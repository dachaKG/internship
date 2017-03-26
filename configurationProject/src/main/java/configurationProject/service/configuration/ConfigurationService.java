package configurationProject.service.configuration;

import java.util.List;

import configurationProject.model.configuration.Configuration;

public interface ConfigurationService {
	
	public void save(Configuration configuration);
	
	public List<Configuration> findAll();
	
	public Configuration getConfigurationById(Long id);

}
