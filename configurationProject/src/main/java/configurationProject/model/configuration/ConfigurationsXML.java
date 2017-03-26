package configurationProject.model.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configurations")
public class ConfigurationsXML {
	
	private List<ConfigurationXML> configurations = new ArrayList<ConfigurationXML>();

	public List<ConfigurationXML> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<ConfigurationXML> configurations) {
		this.configurations = configurations;
	}
	
}
