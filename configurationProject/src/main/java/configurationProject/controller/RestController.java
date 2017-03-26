package configurationProject.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import configurationProject.model.configuration.Configuration;
import configurationProject.model.configuration.ConfigurationXML;
import configurationProject.model.configuration.ConfigurationsXML;
import configurationProject.service.configuration.ConfigurationService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("configurations")
public class RestController {
	
	private ConfigurationService configurationService;
	
	@Autowired
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	@RequestMapping(value = "/sales/{startDatePath}/{endDatePath}")
	public @ResponseBody ConfigurationsXML getConfigurations(@PathVariable String startDatePath, @PathVariable String endDatePath){
		String startDay = startDatePath.substring(0,2);
		String startMonth = startDatePath.substring(2, 4);
		String startYear = startDatePath.substring(4, startDatePath.length());
		
		String startDateString = startYear + "-" + startMonth + "-" + startDay;
		
		String endDay = endDatePath.substring(0,2);
		String endMonth = endDatePath.substring(2, 4);
		String endYear = endDatePath.substring(4, endDatePath.length());
		
		String endDateString = endYear + "-" + endMonth + "-" + endDay;
		
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date startDate;
	    Date endDate;
	    List<Configuration> configurations = new ArrayList<Configuration>();
	    ConfigurationsXML configurationsXml = new ConfigurationsXML();
	    	try {
	    		startDate = df.parse(startDateString);
	    		endDate = df.parse(endDateString);
	    		for(int i = 0 ; i < configurationService.findAll().size(); i++){
	    			if(configurationService.findAll().get(i).getDate().after(startDate) && configurationService.findAll().get(i).getDate().before(endDate)){
	    				configurations.add(configurationService.findAll().get(i));
	    			}
	    		}
	    		System.out.println(configurations.size());
	    		for(int i = 0 ; i < configurations.size(); i++){
	    			ConfigurationXML cXml = new ConfigurationXML();
	    			cXml.setId(configurations.get(i).getId());
	    			cXml.setDate(configurations.get(i).getDate());
	    			cXml.setTotal(configurations.get(i).getTotal());
	    			cXml.setComponent(configurations.get(i).getComponent());
	    			
	    			configurationsXml.getConfigurations().add(cXml);
	    		}
	    		
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    return configurationsXml;
	    		
	}

}
