package configurationProject.model.configuration;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import configurationProject.model.component.Component;

@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.NONE)
public class ConfigurationXML {

	
	public ConfigurationXML(Long id, int total, Date date, List<Component> component) {
		super();
		this.id = id;
		this.total = total;
		this.date = date;
		this.component = component;
	}

	public ConfigurationXML() {
		super();
		// TODO Auto-generated constructor stub
	}

	@XmlAttribute
	private Long id;
	
	@XmlElement
	private int total;
	
	@XmlElement
	private Date date;
	
	@XmlElement
	private List<Component> component;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Component> getComponent() {
		return component;
	}

	public void setComponent(List<Component> component) {
		this.component = component;
	}
	
	

}
